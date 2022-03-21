package com.example.demo.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("t_sys_role")//数据库对应表名
public class Role {
	@TableId
	private String id;//@TableId表示主键，与字段名称大小写一致
	
	private String name;//与字段名称大小写一致

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
