package org.springframework.test.common;

import org.springframework.aop.MethodAfterAdvice;

import java.lang.reflect.Method;

/**
 * @author susanbushisan
 * @version 1.0
 * @date 2021/7/24
 */
public class WorldServiceAfterAdvice implements MethodAfterAdvice {


	@Override
	public void after(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		System.out.println("AfterAdvice: do something after the earth explodes");
	}

}
