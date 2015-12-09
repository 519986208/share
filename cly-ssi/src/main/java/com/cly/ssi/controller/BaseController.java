package com.cly.ssi.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.cly.ssi.springmvc.context.WebContext;

/**
 * 
 * 基础类Controller
 */
public abstract class BaseController {

	protected Logger log = Logger.getLogger(getClass());

	/**
	 * 往前端写Json数据
	 */
	public void WriteToPage(HttpServletResponse response, Object obj) {
		WebContext.getInstance().WriteToPage(response, obj);
	}

}