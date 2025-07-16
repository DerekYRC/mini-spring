package org.springframework.test.common;

import org.springframework.aop.MethodAfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author NingMao
 * @since 2025-07-16
 */
public class WorldServiceAfterReturningAdvice implements MethodAfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("AfterReturningAdvice: do something after the earth explodes return");
    }

}
