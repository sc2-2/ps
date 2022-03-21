package com.example.demo.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("t_sys_permission")//数据库对应表名
public class Permission {
	@TableId
	private String id;//@TableId表示主键，与字段名称大小写一致
	
	private String name;//与字段名称大小写一致
	private String descripion;//与字段名称大小写一致
	private String url;//与字段名称大小写一致
	private String pid;//与字段名称大小写一致
	private String perms;//与字段名称大小写一致
	private Integer type;//与字段名称大小写一致
	private String icon;//与字段名称大小写一致
	private Integer order_num;//与字段名称大小写一致
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
	public String getDescripion() {
		return descripion;
	}
	public void setDescripion(String descripion) {
		this.descripion = descripion;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPerms() {
		return perms;
	}
	public void setPerms(String perms) {
		this.perms = perms;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getOrder_num() {
		return order_num;
	}
	public void setOrder_num(Integer order_num) {
		this.order_num = order_num;
	}

}
