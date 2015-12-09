package com.cly.ssi.entity;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

public class User {

	private int id;
	@NotEmpty(message = "用户名不可为空")
	private String userName;
	private String password;
	private String nickName;
	private String gender;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date updateTIme;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTIme() {
		return updateTIme;
	}

	public void setUpdateTIme(Date updateTIme) {
		this.updateTIme = updateTIme;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}
