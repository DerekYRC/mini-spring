package org.springframework.test.common;

import org.springframework.aop.MethodThrowAdvice;

import java.lang.reflect.Method;

/**
 * @author susanbushisan
 * @version 1.0
 * @date 2021/7/24
 */
public class WorldServiceThrowAdvice implements MethodThrowAdvice {



	@Override
	public void throwThrowable(Method method, Object[] args, Object target, Throwable ex) throws Throwable {
		ex.printStackTrace();
	}
}
