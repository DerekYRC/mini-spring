package org.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author NingMao
 * @since 2025-07-16
 */
public interface MethodAfterReturningAdvice extends AfterReturningAdvice{
    void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable;
}
