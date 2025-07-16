package org.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author NingMao
 * @since 2025-07-16
 */
public interface MethodThrowsAdvice extends ThrowsAdvice{
    void throwsHandle(Throwable throwable, Method method, Object[] args, Object target);
}
