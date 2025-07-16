package org.springframework.aop;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author NingMao
 * @since 2025-07-16
 */
public interface MethodAroundAdvice extends AroundAdvice{
    Object around(MethodInvocation invocation) throws Throwable;
}
