package org.springframework.test.aop;

import org.junit.Test;

import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.test.common.WorldServiceAfterReturnAdvice;
import org.springframework.test.common.WorldServiceBeforeAdvice;
import org.springframework.test.service.WorldService;
import org.springframework.test.service.WorldServiceImpl;

public class ProxyFactoryTest {
	@Test
	public void testAdvisor() throws Exception {
		WorldService worldService = new WorldServiceImpl();

		//Advisor是Pointcut和Advice的组合
		String expression = "execution(* org.springframework.test.service.WorldService.explode(..))";
		//第一个切面
		AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
		advisor.setExpression(expression);
		MethodBeforeAdviceInterceptor methodInterceptor = new MethodBeforeAdviceInterceptor(new WorldServiceBeforeAdvice());
		advisor.setAdvice(methodInterceptor);
		//第二个切面
		AspectJExpressionPointcutAdvisor advisor1 = new AspectJExpressionPointcutAdvisor();
		advisor1.setExpression(expression);
		AfterReturningAdviceInterceptor afterReturningAdviceInterceptor = new AfterReturningAdviceInterceptor(new WorldServiceAfterReturnAdvice());
		advisor1.setAdvice(afterReturningAdviceInterceptor);
		//通过ProxyFactory来获得代理
		ProxyFactory factory = new ProxyFactory();
		TargetSource targetSource = new TargetSource(worldService);
		factory.setTargetSource(targetSource);
		factory.setProxyTargetClass(true);
		factory.addAdvisor(advisor);
		factory.addAdvisor(advisor1);
		WorldService proxy = (WorldService) factory.getProxy();
		proxy.explode();
	}
}
