package org.springframework.test.common;

import org.springframework.aop.MethodThrowsAdvice;

import java.lang.reflect.Method;

/**
 * @author NingMao
 * @since 2025-07-16
 */
public class WorldServiceThrowsAdvice implements MethodThrowsAdvice {

    @Override
    public void throwsHandle(Throwable throwable, Method method, Object[] args, Object target) {
        System.out.println("ThrowsAdvice: do something when the earth explodes function throw an exception");
    }
}
