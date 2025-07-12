package org.springframework.aop;

import org.aopalliance.intercept.MethodInvocation;

/**
 * 环绕通知
 *
 * @author NingMao
 * @since 2025-07-12
 */
public interface AroundAdvice {

    Object around(MethodInvocation invocation) throws Throwable;
}
