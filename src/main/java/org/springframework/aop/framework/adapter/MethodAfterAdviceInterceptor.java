package org.springframework.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodAfterAdvice;

/**
 * @author NingMao
 * @since 2025-07-16
 */
public class MethodAfterAdviceInterceptor implements MethodInterceptor {

    private MethodAfterAdvice advice;

    public MethodAfterAdviceInterceptor() {
    }

    public MethodAfterAdviceInterceptor(MethodAfterAdvice advice) {
        this.advice = advice;
    }

    public void setAdvice(MethodAfterAdvice advice) {
        this.advice = advice;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object retVal = invocation.proceed();
        this.advice.after(invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return retVal;
    }
}
