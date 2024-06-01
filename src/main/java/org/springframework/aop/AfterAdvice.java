package org.springframework.aop;

import org.aopalliance.aop.Advice;

import java.lang.reflect.Method;

/**
 * 后置增强
 *
 * @author zqc
 * @date 2022/12/16
 */
public interface AfterAdvice extends Advice {
    void after(Method method, Object[] args, Object target) throws Throwable;
}
