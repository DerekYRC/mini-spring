package org.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.adapter.*;

/**
 * 组合 advice 拦截器
 *
 * @author NingMao
 * @since 2025-07-16
 */
public class CombineAdviceInterceptor implements MethodInterceptor {

    private MethodBeforeAdviceInterceptor beforeAdviceInterceptor;
    private MethodAfterAdviceInterceptor afterAdviceInterceptor;
    private MethodAfterReturningAdviceInterceptor afterReturningAdviceInterceptor;
    private MethodThrowsAdviceInterceptor throwsAdviceInterceptor;
    private MethodAroundAdviceInterceptor aroundAdviceInterceptor;

    public CombineAdviceInterceptor() {
    }
    public CombineAdviceInterceptor(MethodBeforeAdviceInterceptor beforeAdviceInterceptor,
                                    MethodAfterAdviceInterceptor afterAdviceInterceptor,
                                    MethodAfterReturningAdviceInterceptor afterReturningAdviceInterceptor,
                                    MethodThrowsAdviceInterceptor throwsAdviceInterceptor,
                                    MethodAroundAdviceInterceptor aroundAdviceInterceptor) {
        this.beforeAdviceInterceptor = beforeAdviceInterceptor;
        this.afterAdviceInterceptor = afterAdviceInterceptor;
        this.afterReturningAdviceInterceptor = afterReturningAdviceInterceptor;
        this.throwsAdviceInterceptor = throwsAdviceInterceptor;
        this.aroundAdviceInterceptor = aroundAdviceInterceptor;
    }
    public void setBeforeAdviceInterceptor(MethodBeforeAdviceInterceptor beforeAdviceInterceptor) {
        this.beforeAdviceInterceptor = beforeAdviceInterceptor;
    }

    public void setAfterAdviceInterceptor(MethodAfterAdviceInterceptor afterAdviceInterceptor) {
        this.afterAdviceInterceptor = afterAdviceInterceptor;
    }

    public void setAfterReturningAdviceInterceptor(MethodAfterReturningAdviceInterceptor afterReturningAdviceInterceptor) {
        this.afterReturningAdviceInterceptor = afterReturningAdviceInterceptor;
    }

    public void setThrowsAdviceInterceptor(MethodThrowsAdviceInterceptor throwsAdviceInterceptor) {
        this.throwsAdviceInterceptor = throwsAdviceInterceptor;
    }

    public void setAroundAdviceInterceptor(MethodAroundAdviceInterceptor aroundAdviceInterceptor) { // 更改参数类型
        this.aroundAdviceInterceptor = aroundAdviceInterceptor;
    }
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 如果配置了环绕通知，则优先执行环绕通知
        if (aroundAdviceInterceptor != null) {
            return aroundAdviceInterceptor.invoke(invocation);
        }


        Object result = null; // 用于存储目标方法执行的结果

        try {
            // 前置通知：如果配置了 BeforeAdvice，则在目标方法执行前调用其 before 方法
            if (beforeAdviceInterceptor != null) {
                beforeAdviceInterceptor.invoke(invocation);
            }

            result = invocation.proceed();

        } catch (Throwable throwable) {
            // 异常通知：如果配置了 ThrowsAdvice，则在捕获到异常后调用其 throwsHandle 方法
            if (throwsAdviceInterceptor != null) {
                // 将捕获到的异常、方法、参数和目标对象传递给异常通知处理器
                throwsAdviceInterceptor.invoke(invocation);
            }
            throw throwable; // 重新抛出异常，确保异常行为传播
        } finally {
            // 后置通知：无论目标方法是否抛出异常，都会在方法执行结束后执行此处的逻辑
            if (afterAdviceInterceptor != null) {
                afterAdviceInterceptor.invoke(invocation);
            }
        }

        // 返回通知：仅在目标方法成功执行（没有抛出异常）并返回结果后执行此处的逻辑
        // 注意：此部分位于 try-catch-finally 块之外，确保只有成功返回才触发
        if (afterReturningAdviceInterceptor != null) {
            afterReturningAdviceInterceptor.invoke(invocation);
        }

        // 返回目标方法的执行结果
        return result;
    }

}

