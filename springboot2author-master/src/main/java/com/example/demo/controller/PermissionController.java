package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.model.Permission;
import com.example.demo.model.Role;
import com.example.demo.model.RolePermission;
import com.example.demo.service.PermissionService;
import com.example.demo.vo.DataTableResult;
import com.example.demo.vo.Json;
import com.example.demo.vo.PermissionVO;
import com.example.demo.vo.ZTreeNode;

@Controller
@RequestMapping("/PermissionController")
public class PermissionController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private String prefix = "admin/permission";// 页面的路径

	@Autowired
	private PermissionService permissionService;// 注入业务层的service

	// 未加入@ResponseBody用来返回数据给页面
	@RequestMapping("view")
	public String view(Model model) {
		return prefix + "/view";
	}

	// @ResponseBody，直接通过js异步返回数据给页面
	@RequestMapping("list")
	@ResponseBody
	public DataTableResult list(HttpServletRequest request, PermissionVO permissionVO) {
		// DataTableResult返回给datatables控件的数据格式
		DataTableResult result = new DataTableResult();
		// 获取分页参数
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		// 获取排序字段
		String orderIdx = request.getParameter("order[0][column]");
		// 获取排序字段名
		String orderField = request.getParameter("columns[" + orderIdx + "][name]");
		// 获取排序方式，降序desc或者升序asc
		String orderDir = request.getParameter("order[0][dir]");
		result = permissionService.selectPermissionListPage(permissionVO, start, length, orderField, orderDir);
//		result.setDraw(permissionVO.getDraw());
		return result;
	}

	// @ResponseBody，直接通过js异步返回数据给页面
	@RequestMapping("insert")
	@ResponseBody
	public Json insert(Permission permission) {
		Json j = new Json();
		if (permissionService.insert(permission) > 0) {
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} else {
			j.setSuccess(false);
			j.setMsg("添加失败！");
		}
		return j;
	}

	// @ResponseBody，直接通过js异步返回数据给页面
	@RequestMapping("update")
	@ResponseBody
	public Json updateById(Permission permission) {
		Json j = new Json();
		if (permissionService.updateById(permission) > 0) {
			j.setSuccess(true);
			j.setMsg("修改成功！");
		} else {
			j.setSuccess(false);
			j.setMsg("修改失败！");
		}
		return j;
	}

	// @ResponseBody，直接通过js异步返回数据给页面
	@RequestMapping("select")
	@ResponseBody
	public Json selectById(Permission permission) {
		Json j = new Json();
		j.setSuccess(true);
		j.setObj(permissionService.selectById(permission.getId()));
		return j;
	}

	// @ResponseBody，直接通过js异步返回数据给页面
	@RequestMapping("delete")
	@ResponseBody
	public Json delete(HttpServletRequest request) {
		Json j = new Json();
		String ids = request.getParameter("ids");
		if (!StringUtils.isEmpty(ids)) {
			j.setSuccess(true);
			j.setObj("成功删除" + permissionService.delete(ids) + "条记录");
		} else {
			j.setSuccess(false);
			j.setMsg("没有需要删除的记录！");
		}
		return j;
	}

	/**
	 * 获取树形结构
	 **/
	@RequestMapping("tree")
	@ResponseBody
	public List<ZTreeNode> getTreeList(HttpServletRequest request, Role role) {
		List<Permission> permissionList = permissionService.selectList();
		List<RolePermission> rolePermissionsList = permissionService.selectRolePermission(role);
		List<ZTreeNode> list = new ArrayList<ZTreeNode>();
		for (Permission p : permissionList) {
			ZTreeNode zt = new ZTreeNode();
			zt.setId(p.getId());
			zt.setName(p.getName());
			zt.setOpen(true);
			zt.setpId(p.getPid());
			if (p.getPid().equals("0"))
				zt.setIsParent(true);
			else
				zt.setIsParent(false);
			zt.setMsg(p.getDescripion() + "");
			// 查询是否已经分配了权限,有的打勾
			for (RolePermission rp : rolePermissionsList) {
				if (rp.getSys_permission_id().equals(p.getId())) {
					zt.setChecked(true);
					break;
				}
			}
			list.add(zt);
		}
		return list;
	}

	// @ResponseBody，直接通过js异步返回数据给页面
	@RequestMapping("author")
	@ResponseBody
	public Json author(HttpServletRequest request, Role role) {
		Json j = new Json();
		String ids = request.getParameter("ids");
		if (!StringUtils.isEmpty(role.getId())) {
			permissionService.author(role, ids);
			j.setSuccess(true);
			j.setObj("分配权限成功！");
		} else {
			j.setSuccess(false);
			j.setMsg("角色id为空！");
		}
		return j;
	}

}
