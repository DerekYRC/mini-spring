package org.springframework.test.common;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AroundAdvice;

/**
 * @author NingMao
 * @since 2025-07-12
 */
public class WorldServiceAroundAdvice implements AroundAdvice {
    @Override
    public Object around(MethodInvocation invocation) throws Throwable {
        System.out.println("--- 环绕通知：方法执行前操作 ---");
        Object result;
        try {
            // 调用链的下一个方法（可能是目标方法，也可能是其他普通通知）
            result = invocation.proceed();
            System.out.println("--- 环绕通知：方法成功返回后操作 ---");
        } catch (Throwable e) {
            System.out.println("--- 环绕通知：方法抛出异常后操作： " + e.getMessage() + " ---");
            throw e; // 确保异常继续传播
        } finally {
            System.out.println("--- 环绕通知：方法最终结束操作 ---");
        }
        return result;
    }
}
