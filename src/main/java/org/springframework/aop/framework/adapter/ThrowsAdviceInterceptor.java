package org.springframework.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodThrowAdvice;

/**
 * @author susanbushisan
 * @version 1.0
 * @date 2021/7/24
 */
public class ThrowsAdviceInterceptor implements MethodInterceptor {

    private MethodThrowAdvice advice;

    public ThrowsAdviceInterceptor(MethodThrowAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            return invocation.proceed();
        } catch (Throwable ex) {
            advice.throwThrowable(invocation.getMethod(), invocation.getArguments(), invocation.getThis(), ex);
            throw ex;
        }
    }
}
