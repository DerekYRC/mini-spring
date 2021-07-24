package org.springframework.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodAfterAdvice;

/**
 * @author susanbushisan
 * @version 1.0
 * @date 2021/7/24
 */
public class MethodAfterAdviceInterceptor implements MethodInterceptor {

    private MethodAfterAdvice advice;

    public MethodAfterAdviceInterceptor(MethodAfterAdvice advice) {
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        //在执行被代理方法之前，先执行before advice操作
        Object returnVal = invocation.proceed();
        this.advice.after(returnVal, invocation.getMethod(), invocation.getArguments(), invocation.getThis());
        return returnVal;
    }
}
