package org.springframework.test.common;

import org.springframework.aop.AfterAdvice;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * @author derekyi
 * @date 2020/12/6
 */
public class WorldServiceThrowsAdvice implements ThrowsAdvice {

    @Override
    public void throwsHandle(Throwable throwable, Method method, Object[] args, Object target) {
        System.out.println("ThrowsAdvice: do something when the earth explodes function throw an exception");
    }
}
