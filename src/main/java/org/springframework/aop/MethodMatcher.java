package org.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author derekyi
 * @date 2020/12/5
 */
public interface MethodMatcher {

	boolean matches(Method method, Class<?> targetClass);
}
