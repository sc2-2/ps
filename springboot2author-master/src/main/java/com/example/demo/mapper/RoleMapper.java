package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.demo.model.Role;
import com.example.demo.vo.RoleVO;

@Mapper
public interface  RoleMapper  extends BaseMapper<Role>{
	 List<RoleVO> selectRoleListPage(Pagination page ,RoleVO roleVO);
}
