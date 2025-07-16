package org.springframework.test.common;

import org.springframework.aop.MethodAfterAdvice;

import java.lang.reflect.Method;

/**
 * @author NingMao
 * @since 2025-07-16
 */
public class WorldServiceAfterAdvice implements MethodAfterAdvice {
    @Override
    public void after(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("AfterAdvice: do something after the earth explodes");
    }
}
