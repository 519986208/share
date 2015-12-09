package com.cly.common.exception;

import org.apache.log4j.Logger;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 7301448251545592630L;

	private final Logger log = Logger.getLogger(getClass());

	public BusinessException(String message) {
		super(message);
		log.error(message);
	}

	public BusinessException(Exception e) {
		super(e);
		log.error(e);
	}
}
