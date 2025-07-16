package org.springframework.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;

/**
 * @author NingMao
 * @since 2025-07-16
 */
public class MethodAfterReturningAdviceInterceptor implements MethodInterceptor {

    private AfterReturningAdvice advice;

    public MethodAfterReturningAdviceInterceptor() {
    }

    public MethodAfterReturningAdviceInterceptor(AfterReturningAdvice advice) {
        this.advice = advice;
    }

    public void setAdvice(AfterReturningAdvice advice) {
        this.advice = advice;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object retVal = invocation.proceed();
        this.advice.afterReturning(retVal, invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return retVal;
    }
}