package com.cly.ssi.springmvc.valid;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import javax.validation.Valid;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * Aop拦截 jsr303验证<br>
 * 拦截controller层，标有注解Validate的方法，并且方法中的参数有注解@Valid和BindingResult
 *
 */
@Service
@Aspect
public class ValidAspect {

	@Around("@annotation(com.cly.common.annotation.Validate)")
	public Object jsrValid(ProceedingJoinPoint pjp) throws Throwable {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		Object[] args = pjp.getArgs();
		Annotation[][] annotations = method.getParameterAnnotations();
		for (int i = 0; i < annotations.length; i++) {
			if (!hasValidAnnotation(annotations[i]))
				continue;
			if (!(i < annotations.length - 1 && args[i + 1] instanceof BindingResult)) {
				// 验证对象后面没有跟bindingResult,事实上如果没有应该到不了这一步
				continue;
			}
			BindingResult result = (BindingResult) args[i + 1];
			if (result.hasErrors()) {
				List<FieldError> fieldErrors = result.getFieldErrors();
				for (FieldError fieldError : fieldErrors)
					throw new RuntimeException(fieldError.getDefaultMessage());
			}
		}
		return pjp.proceed();
	}

	private boolean hasValidAnnotation(Annotation[] annotations) {
		if (annotations == null)
			return false;
		for (Annotation annotation : annotations) {
			if (annotation instanceof Valid)
				return true;
		}
		return false;
	}

}
