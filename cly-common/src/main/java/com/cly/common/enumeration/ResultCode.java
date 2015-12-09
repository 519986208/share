package com.cly.common.enumeration;

/**
 * 
 * 系统接口返回的状态码
 */
public enum ResultCode {

	SUCCESS(0, "成功"), // <br>
	FAIL(120001, "失敗"), // <br>
	SYSTEM_ERROR(120002, "系统异常"), // <br>
	AUTHORIZED_ERROR(120003, "没有权限"), // <br>
	PARAMES_ERROR(120004, "参数异常"), // <br>
	UPLOAD_ERROR(120005, "文件上传异常"), // <br>
	DOWNLOAD_ERROR(120006, "文件下载异常"), // <br>
	KEY_ERROR(120007, "身份认证失败"), // <br>
	DES_ERROR(120008, "异或码校验失败"), // <br>
	LEN_ERROR(120009, "数据长度校验失败"), // <br>
	NUL_ERROR(120010, "缺少参数或参数为空"), // <br>
	EXISTS_EMOJI(120011, "输入的内容里存在系统不支持的字符")// <br>
	;

	private int code;// 描述编码
	private String message;// 描述信息

	private ResultCode(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

}
