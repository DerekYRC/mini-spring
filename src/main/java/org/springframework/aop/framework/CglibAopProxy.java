package org.springframework.aop.framework;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.aop.AdvisedSupport;

import java.lang.reflect.Method;

/**
 * cgli动态代理
 *
 * @author derekyi
 * @date 2020/12/6
 */
public class CglibAopProxy implements AopProxy {

	private final AdvisedSupport advised;

	public CglibAopProxy(AdvisedSupport advised) {
		this.advised = advised;
	}


	@Override
	public Object getProxy() {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(advised.getTargetSource().getTarget().getClass());
		enhancer.setInterfaces(advised.getTargetSource().getTargetClass());
		enhancer.setCallback(new DynamicAdvisedInterceptor(advised));
		return enhancer.create();
	}

	/**
	 * 注意此处的MethodInterceptor是cglib中的接口，advised中的MethodInterceptor的AOP联盟中定义的接口，因此定义此类做适配
	 */
	private static class DynamicAdvisedInterceptor implements MethodInterceptor {

		private final AdvisedSupport advised;

		private DynamicAdvisedInterceptor(AdvisedSupport advised) {
			this.advised = advised;
		}

		@Override
		public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
			CglibMethodInvocation methodInvocation = new CglibMethodInvocation(advised.getTargetSource().getTarget(), method, args, methodProxy);
			methodInvocation.setMethodInterceptorList(advised.getMethodInterceptorList());
			if (advised.getMethodMatcher().matches(method, advised.getTargetSource().getTarget().getClass())) {
				//代理方法
				return methodInvocation.proceed();
			}
			return method.invoke(advised.getTargetSource().getTarget(), args);
		}
	}

	private static class CglibMethodInvocation extends ReflectiveMethodInvocation {

		private final MethodProxy methodProxy;

		public CglibMethodInvocation(Object target, Method method, Object[] arguments, MethodProxy methodProxy) {
			super(target, method, arguments);
			this.methodProxy = methodProxy;
		}

		@Override
		public Object proceed() throws Throwable {
			if (count > methodInterceptorList.size()) {
				// 调用目标， 返回并结束递归
				return this.methodProxy.invoke(this.target, this.arguments);
			}
			// 逐一调用通知, count + 1
			org.aopalliance.intercept.MethodInterceptor methodInterceptor = methodInterceptorList.get(count++ - 1);
			return methodInterceptor.invoke(this);
		}
	}
}
