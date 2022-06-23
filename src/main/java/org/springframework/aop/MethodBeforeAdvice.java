package org.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author derekyi
 * @date 2020/12/6
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

	void before(Method method, Object[] args, Object target) throws Throwable;
}
