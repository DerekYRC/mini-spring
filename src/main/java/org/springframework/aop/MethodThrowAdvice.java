package org.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author susanbushisan
 * @version 1.0
 * @date 2021/7/24
 */
public interface MethodThrowAdvice extends AfterAdvice {
    void throwThrowable(Method method, Object[] args, Object target,Throwable ex) throws Throwable;
}
