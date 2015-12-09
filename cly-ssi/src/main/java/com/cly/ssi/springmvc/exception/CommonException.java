package com.cly.ssi.springmvc.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.cly.common.web.json.Result;
import com.cly.ssi.springmvc.context.WebContext;

@Service
public class CommonException implements HandlerExceptionResolver {

	private Logger log = Logger.getLogger(getClass());

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		Result result = new Result();
		result.setMessage(ex.getMessage());
		result.setCode(500);
		WebContext.getInstance().WriteToPage(response, result);
		log.error(ex +"\n");
		return null;
	}

}