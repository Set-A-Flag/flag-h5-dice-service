package com.chasel.h5.dice.vo;

/**
 * 返回状态
 * 
 * @author chasel
 *
 */
public enum ResponseStatus {

	// 成功状态
	SUCCESS(200),

	// 失败状态
	FAIL(500),
	
	//非登录转态
	NOT_LOGIN(403);

	private int code;

	public int getCode() {
		return code;
	}

	ResponseStatus(int code) {
		this.code = code;
	}

}
