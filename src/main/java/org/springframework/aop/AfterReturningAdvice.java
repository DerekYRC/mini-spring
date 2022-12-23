package org.springframework.aop;

import org.springframework.aop.AfterAdvice;

import java.lang.reflect.Method;

/**
 * @author zqc
 * @date 2022/12/16
 */
public interface AfterReturningAdvice extends AfterAdvice {
    void afterReturning( Object returnValue, Method method, Object[] args,  Object target) throws Throwable;
}
