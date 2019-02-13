package com.chasel.h5.dice.vo;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Base {
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp createTime;
	private String creator;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp updateTime;
	private String updator;
	
	public Base() {
	}

	public Timestamp getCreateTime() {
		return (Timestamp) createTime.clone();
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = (Timestamp) createTime.clone();
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Timestamp getUpdateTime() {
		return (Timestamp) updateTime.clone();
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = (Timestamp) updateTime.clone();
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	@Override
	public String toString() {
		return "Base [createTime=" + createTime + ", creator=" + creator + ", updateTime=" + updateTime + ", updator="
				+ updator + "]";
	}
	
}
