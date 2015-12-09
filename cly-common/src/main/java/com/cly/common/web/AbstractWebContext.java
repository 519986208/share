package com.cly.common.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

public abstract class AbstractWebContext {

	/**
	 * 获取request
	 */
	public abstract HttpServletRequest getRequest();

	/**
	 * 获取session
	 */
	public abstract HttpSession getSession();

	/**
	 * 获取请求路径
	 */
	public abstract String getRequestPath();

	/**
	 * 往前端写Json数据
	 */
	public void WriteToPage(HttpServletResponse response, Object obj) {
		if (response.isCommitted()) {
			return;
		}
		PrintWriter writer = null;
		try {
			response.setCharacterEncoding("utf-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			writer = response.getWriter();
			writer.write(JSON.toJSONString(obj));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

}
