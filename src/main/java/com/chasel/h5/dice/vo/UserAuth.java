package com.chasel.h5.dice.vo;

import java.io.Serializable;

public class UserAuth extends Base implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//id
	private long id;
	//用户ID
	private long userId;
	//用户类型
	private String identityType;
	//用户凭证
	private String identifier;
	//密码标识
	private String credential;
	
	public UserAuth() {
		
	}

	public UserAuth(long userId, String identityType, String identifier, String credential) {
		super();
		this.userId = userId;
		this.identityType = identityType;
		this.identifier = identifier;
		this.credential = credential;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getIdentityType() {
		return identityType;
	}

	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getCredential() {
		return credential;
	}

	public void setCredential(String credential) {
		this.credential = credential;
	}

	@Override
	public String toString() {
		return "UserAuth [id=" + id + ", userId=" + userId + ", identityType=" + identityType + ", identifier="
				+ identifier + ", credential=" + credential + "]";
	}
	
}
