package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.mapper.PermissionMapper;
import com.example.demo.mapper.RolePermissionMapper;
import com.example.demo.model.Permission;
import com.example.demo.model.Role;
import com.example.demo.model.RolePermission;
import com.example.demo.util.Convert;
import com.example.demo.util.SnowflakeIdWorker;
import com.example.demo.vo.DataTableResult;
import com.example.demo.vo.Menu;
import com.example.demo.vo.MenuList;
import com.example.demo.vo.PermissionVO;

@Service
public class PermissionService {

	@Autowired
	private PermissionMapper permissionMapper;// 注入mapper进行数据操作

	@Autowired
	private RolePermissionMapper rolePermissionMapper;// 注入mapper进行数据操作

	// 对于执行数据修改的方法加上事务处理
	@Transactional
	public int delete(String ids) {
		// ids,逗号隔开的主键
		List<String> listid = Convert.toListStrArray(ids);
		return permissionMapper.deleteBatchIds(listid);
	}

	public Permission selectById(String id) {
		// permissionMapper.selectOne(permission),selectOne可以按照其他字段来查询一条记录
		return permissionMapper.selectById(id);
	}

	// 对于执行数据修改的方法加上事务处理
	@Transactional
	public int updateById(Permission permission) {
		return permissionMapper.updateById(permission);
	}

	// 对于执行数据修改的方法加上事务处理
	@Transactional
	public int insert(Permission permission) {
		// 添加雪花主键id
		permission.setId(SnowflakeIdWorker.getUUID());
		int n = permissionMapper.insert(permission);
//			n=1/0; //事务测试
		return n;
	}

	/**
	 * 采用集成的查询方法
	 * 
	 * @param username
	 * @return
	 */
	public List<Permission> selectList() {
		EntityWrapper<Permission> wrapper = new EntityWrapper<Permission>();
//	    wrapper.like("username", username);
		return permissionMapper.selectList(wrapper);
	}

	/**
	 * 返回角色所有权限
	 * 
	 * @param role
	 * @return
	 */
	public List<RolePermission> selectRolePermission(Role role) {
		EntityWrapper<RolePermission> wrapper = new EntityWrapper<RolePermission>();
		wrapper.eq("sys_role_id", role.getId());// 可以多个条件
		return rolePermissionMapper.selectList(wrapper);
	}

	// 对于执行数据修改的方法加上事务处理
	@Transactional
	public void author(Role role, String ids) {
		// 1.清除原有分配
		EntityWrapper<RolePermission> wrapper = new EntityWrapper<RolePermission>();
		wrapper.eq("sys_role_id", role.getId());// 可以多个条件
		rolePermissionMapper.delete(wrapper);
		// 2.重新插入权限
		if (!StringUtils.isEmpty(ids)) {
			List<String> listrid = Convert.toListStrArray(ids);
			SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);
			for (String pid : listrid) {
				RolePermission rp = new RolePermission();
				rp.setId(String.valueOf(idWorker.nextId()));
				rp.setSys_role_id(role.getId());
				rp.setSys_permission_id(pid);
				rolePermissionMapper.insert(rp);
			}
		}
	}

	/**
	 * 分页查询
	 * 
	 * @param permission
	 * @return
	 */
	public DataTableResult selectPermissionListPage(PermissionVO permissionVO, int start, int length, String orderField,
			String orderDir) {
		Page<PermissionVO> page = null;
		// 排序
		if (!StringUtils.isEmpty(orderDir) && !StringUtils.isEmpty(orderField)) {
			if (orderDir.equals("asc")) {
				page = new Page<>(start / length + 1, length, orderField, true);// 当前页，每页总条数 构造 page 对象
			} else {
				page = new Page<>(start / length + 1, length, orderField, false);// 当前页，每页总条数 构造 page 对象
			}

		} else {
			page = new Page<>(start / length + 1, length, "id", false);// 默认id降序
		}
		page.setRecords(permissionMapper.selectPermissionListPage(page, permissionVO));

		DataTableResult result = new DataTableResult();
		result.setRecordsTotal(page.getTotal());
		result.setRecordsFiltered(page.getTotal());
		result.setData(page.getRecords());
		return result;
	}
	
	public List<Permission> getRootMenu() {
		EntityWrapper<Permission> wrapper = new EntityWrapper<Permission>();
	    wrapper.eq("pid", 0);
		return permissionMapper.selectList(wrapper);
	}
	public List<Permission> getSubMenu(String pid) {
		EntityWrapper<Permission> wrapper = new EntityWrapper<Permission>();
		wrapper.eq("pid", pid);
		return permissionMapper.selectList(wrapper);
	}
	public List<MenuList> getUserMenu(String userid){
		List<MenuList> menulist = new ArrayList<MenuList>();
		//1.查询用户的菜单id
		List<PermissionVO> userPermissionList = permissionMapper.selectUserPermissionList(userid);
		//2.构造菜单结构
		List<Permission> rootMenuList = getRootMenu();
		for(Permission p:rootMenuList) {
			//判断是否有权限
			boolean flag = false;
			for(PermissionVO pv:userPermissionList) {
				if(pv.getId().equals(p.getId())) {
					flag = true;
					break;
				}
			}
			if(flag) {
				//构造菜单
				Menu menu=new Menu();
				menu.setMenuIcon(p.getIcon());
				menu.setMenuText(p.getName());
				menu.setMenuUrl(p.getUrl());
				//有权限，获取下级菜单
				List<Permission> subMenuList = getSubMenu(p.getId());
				List<Menu> menulistsub = new ArrayList<Menu>();//构造下级菜单
				for(Permission p2:subMenuList) {
					//判断是否有权限
					boolean flag2 = false;
					for(PermissionVO pv2:userPermissionList) {
						if(pv2.getId().equals(p2.getId())) {
							flag2 = true;
							break;
						}
					}
					if(flag2) {
						//有权限
						//构造二级菜单
						Menu menu2=new Menu();
						menu2.setMenuIcon(p2.getIcon());
						menu2.setMenuText(p2.getName());
						menu2.setMenuUrl(p2.getUrl());
						menulistsub.add(menu2);
					}
				}
				MenuList mList =new MenuList();
				mList.setMenu(menu);
				mList.setSubmenus(menulistsub);
				menulist.add(mList);
			}
		}
		return menulist;
	}

}
