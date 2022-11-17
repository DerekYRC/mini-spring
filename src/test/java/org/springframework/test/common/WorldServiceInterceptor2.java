package org.springframework.test.common;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
* @Description:     Spring底层会将所有的通知类型都转化成 MethodInterceptor 环绕通知
* @CreateDate:     2022/11/15 20:41
* @Author:         Songzm1999
*/
public class WorldServiceInterceptor2 implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("Do something before the earth explodes2");
        Object result = invocation.proceed();
        System.out.println("Do something after the earth explodes2");
        return result;
    }

}
