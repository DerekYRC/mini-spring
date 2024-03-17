package org.springframework.test.common;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @Author:sfy
 * @Date: 2024/3/18 - 2:35
 * Description:
 */
public class BAfterReturningAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println("B afterRunningAdvice has done...");
    }
}
