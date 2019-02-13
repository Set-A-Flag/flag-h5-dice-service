package com.chasel.h5.dice.vo;

/**
 * 返回结果
 * 
 * @author chasel
 *
 */
public class ResponseResult {
	// 成功状态
	private ResponseStatus status;

	// 详细描述
	private String desc;

	// 返回信息体
	private Object result;

	public ResponseResult() {
	}

	public ResponseResult(ResponseStatus status, String desc) {
		super();
		this.status = status;
		this.desc = desc;
	}

	public ResponseResult(ResponseStatus status, String desc, Object result) {
		super();
		this.status = status;
		this.desc = desc;
		this.result = result;
	}

	public int getStatus() {
		return status.getCode();
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
