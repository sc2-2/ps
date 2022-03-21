package com.example.demo.vo;

import java.sql.Timestamp;

/**
 * 展示模型（一般不做修改操作），不一定与物理表字段一致，可以是几张关联表的字段组合
 * @author gjq
 *
 */
public class UserVO{

	private String id;
	
	private String username;
	private String password;
	
    private Timestamp birthday;
	
	private String photo;
	private String introduce;
	private String usertype;
	
//	private String rolename;//关联角色表中的name字段，用来存放角色名

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
	public String getId() {
		return id;
	}	

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getBirthday() {
		return birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

//	public String getRolename() {
//		return rolename;
//	}
//
//	public void setRolename(String rolename) {
//		this.rolename = rolename;
//	}
	
	
}
