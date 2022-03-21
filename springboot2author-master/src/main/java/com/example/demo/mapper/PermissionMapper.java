package com.example.demo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.demo.model.Permission;
import com.example.demo.vo.PermissionVO;

@Mapper
public interface  PermissionMapper  extends BaseMapper<Permission>{
	 List<PermissionVO> selectPermissionListPage(Pagination page ,PermissionVO permissionVO);
	 List<PermissionVO> selectUserPermissionList(String userid);
}
