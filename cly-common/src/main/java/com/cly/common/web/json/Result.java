package com.cly.common.web.json;

/**
 * Json结果对象
 * 
 */
public class Result {

	private int code;// 描述代码

	private String message;// 描述信息

	private Object data;// 提供内容封装

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
