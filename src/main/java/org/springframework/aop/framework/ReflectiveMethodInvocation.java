package org.springframework.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author derekyi
 * @date 2020/12/6
 */
public class ReflectiveMethodInvocation implements MethodInvocation {

	protected final Object target;

	protected final Method method;

	protected final Object[] arguments;

	protected List<MethodInterceptor> methodInterceptorList;
	// 调用次数
	protected int count = 1;

	public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
		this.target = target;
		this.method = method;
		this.arguments = arguments;
	}

	@Override
	public Object proceed() throws Throwable {
		/*
			|-> before1 ----------------------------------- 不断从拦截器列表中获取 MethodInvocation
			|                                             |
			|   |-> before2 --------------------          | 不断从拦截器列表中获取 MethodInvocation
			|   |                              |          |
			|   |   |-> target ------ 目标   advice2    advice1
			|   |                              |          |
			|   |-> after2 ---------------------          |
			|                                             |
			|-> after1 ------------------------------------
		 */
		if (count > methodInterceptorList.size()) {
			// 调用目标， 返回并结束递归
			return method.invoke(target, arguments);
		}
		// 如果拦截器还没有调用完成，就逐一调用通知, count + 1
		MethodInterceptor methodInterceptor = methodInterceptorList.get(count++ - 1);
		return methodInterceptor.invoke(this);
	}

	@Override
	public Method getMethod() {
		return method;
	}

	@Override
	public Object[] getArguments() {
		return arguments;
	}

	@Override
	public Object getThis() {
		return target;
	}

	@Override
	public AccessibleObject getStaticPart() {
		return method;
	}

	public void setMethodInterceptorList(List<MethodInterceptor> methodInterceptorList) {
		this.methodInterceptorList = methodInterceptorList;
	}
}
