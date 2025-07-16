package org.springframework.aop.framework.adapter;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodThrowsAdvice;

/**
 * @author NingMao
 * @since 2025-07-16
 */
public class MethodThrowsAdviceInterceptor implements MethodInterceptor {

    private MethodThrowsAdvice advice;

    public MethodThrowsAdviceInterceptor() {
    }

    public MethodThrowsAdviceInterceptor(MethodThrowsAdvice advice) {
        this.advice = advice;
    }

    public void setAdvice(MethodThrowsAdvice advice) {
        this.advice = advice;
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            return invocation.proceed();
        }
        catch (Throwable ex) {
            this.advice.throwsHandle(ex, invocation.getMethod(), invocation.getArguments(), invocation.getThis());
            throw ex;
        }
    }

}
