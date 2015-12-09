package com.cly.ssi.springmvc.aop;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cly.ssi.springmvc.context.WebContext;

/**
 * 定义切面，记录日志<br>
 * 拦截http请求与返回值
 *
 */
@Service
@Aspect
public class LogAspect {

	private final Logger log = Logger.getLogger(getClass());

	/**
	 * 线程级别，放置环绕通知的请求
	 */
	private ThreadLocal<StringBuilder> httpRequest = new ThreadLocal<StringBuilder>();

	/**
	 * 拦截com.baoli.weicomm下所有的controller里的任何类任何方法
	 */
	@Pointcut("execution(public * com.cly..*.controller.*.*(..))")
	private void pointCutMethod() {
	}

	/**
	 * 声明后置通知<br>
	 * 返回方法的返回值<br>
	 * 记录到日志文件中
	 */
	@AfterReturning(pointcut = "pointCutMethod()", returning = "returnValue")
	public void after(Object returnValue) {
		StringBuilder builder = httpRequest.get();
		builder.append("方法返回值：");
		builder.append(JSON.toJSONString(returnValue));
		builder.append("\n");
		log.info(builder.toString());
	}

	/**
	 * 声明环绕通知<br>
	 * 拦截请求路径<br>
	 * 拦截http请求方法<br>
	 * 拦截类名<br>
	 * 拦截方法名<br>
	 * 拦截请求参数<br>
	 * 拦截RequestBody
	 */
	@Around("pointCutMethod()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		String requestPath = WebContext.getInstance().getRequestPath();
		String method = WebContext.getInstance().getRequest().getMethod();
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		StringBuilder builder = new StringBuilder();
		builder.append("\n");
		builder.append("请求路径：" + requestPath).append("\n");
		builder.append("http请求方式：" + method).append("\n");
		builder.append("类名：" + targetName).append("\n");
		builder.append("方法名：" + methodName).append("\n");
		builder.append("参数：");
		int length = arguments.length;
		for (int i = 0; i < length; i++) {
			Object obj = arguments[i];
			if (obj == null)
				continue;
			String value = obj.toString();
			if (i == length - 1) {
				builder.append(value);
			} else {
				builder.append(value).append(" ");
			}
		}
		builder.append("\n");
		httpRequest.set(builder);
		return joinPoint.proceed();
	}

}