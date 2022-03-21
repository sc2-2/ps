package com.example.demo.model;
import java.sql.Timestamp;

/**
 * 与数据库中的物理表映射，增删改一般都用这个模型实现
 */
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

@TableName("t_sys_user")//数据库对应表名
public class User {
	
	@TableId
	private String id;//@TableId表示主键，与字段名称大小写一致
	
	private String username;//与字段名称大小写一致
	private String password;//与字段名称大小写一致
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp birthday;//与字段名称大小写一致
	
	private String photo;//与字段名称大小写一致
	private String introduce;//与字段名称大小写一致
	private String usertype;//与字段名称大小写一致
	
	
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
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
	
}
