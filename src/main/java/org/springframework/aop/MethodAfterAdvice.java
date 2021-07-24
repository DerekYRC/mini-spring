package org.springframework.aop;

import java.lang.reflect.Method;

/**
 * @author susanbushisan
 * @version 1.0
 * @date 2021/7/24
 */
public interface MethodAfterAdvice extends AfterAdvice {
    void after(Object returnValue, Method method, Object[] args, Object target) throws Throwable;
}
