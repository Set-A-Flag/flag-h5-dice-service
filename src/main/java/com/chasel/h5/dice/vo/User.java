package com.chasel.h5.dice.vo;

import java.io.Serializable;
public class User extends Base implements Serializable {
	private static final long serialVersionUID = 1L;
	//id
	private Long id;
	//账号
	private String account;
	//昵称
	private String nickName;
	//手机
	private String phone;
	//email
	private String email;
	//角色（1-超级管理员，2-普通管理员，0-普通用户）
	private int role;
	//密码
	private String password;

	public User() {
		
	}

	public User(Long id, String account, String nickName, String phone, String email, int role, String password) {
		super();
		this.id = id;
		this.account = account;
		this.nickName = nickName;
		this.phone = phone;
		this.email = email;
		this.role = role;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", nickName=" + nickName + ", phone=" + phone + ", email="
				+ email + ", role=" + role + ", password=" + password + "]";
	}
	
}
