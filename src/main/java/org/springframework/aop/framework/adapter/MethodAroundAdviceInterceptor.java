package org.springframework.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodAroundAdvice;

/**
 * @author NingMao
 * @since 2025-07-16
 */
public class MethodAroundAdviceInterceptor implements MethodInterceptor {

    private MethodAroundAdvice advice;

    public MethodAroundAdviceInterceptor() {
    }

    public MethodAroundAdviceInterceptor(MethodAroundAdvice advice) {
        this.advice = advice;
    }

    public void setAdvice(MethodAroundAdvice advice) {
        this.advice = advice;
    }
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return advice.around(invocation);
    }
}
