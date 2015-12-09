package com.cly.ssi.springmvc.context;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cly.common.web.AbstractWebContext;

/**
 * 基于springmvc web上下文环境
 *
 */
public class WebContext extends AbstractWebContext {

	private WebContext() {

	}

	private static WebContext instance = new WebContext();

	public static WebContext getInstance() {
		return instance;
	}

	/**
	 * 获取request
	 */
	public HttpServletRequest getRequest() {
		ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		return attrs.getRequest();
	}

	/**
	 * 获取session
	 */
	public HttpSession getSession() {
		HttpServletRequest request = getRequest();
		return request.getSession();
	}

	/**
	 * 获取请求路径
	 */
	public String getRequestPath() {
		HttpServletRequest httpServletRequest = getRequest();
		StringBuilder builder = new StringBuilder();
		String scheme = httpServletRequest.getScheme();
		String serverName = httpServletRequest.getServerName();
		int serverPort = httpServletRequest.getServerPort();
		String requestURI = httpServletRequest.getRequestURI();
		builder.append(scheme);
		builder.append("://");
		builder.append(serverName);
		builder.append(":");
		builder.append(serverPort);
		builder.append(requestURI);

		@SuppressWarnings("unchecked")
		Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
		boolean hasMoreElements = parameterNames.hasMoreElements();
		if (hasMoreElements) {
			builder.append("?");
		}
		while (parameterNames.hasMoreElements()) {
			String element = parameterNames.nextElement();
			builder.append(element);
			builder.append("=");
			builder.append(httpServletRequest.getParameter(element));
			builder.append("&");
		}
		String str = builder.toString();
		if (hasMoreElements) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}

}
