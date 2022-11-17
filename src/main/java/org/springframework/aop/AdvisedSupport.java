package org.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author derekyi
 * @date 2020/12/6
 */
public class AdvisedSupport {

	private TargetSource targetSource;

	private MethodMatcher methodMatcher;

	/**
	 * 为了实现一个方法有多个拦截器
	 */
	private List<MethodInterceptor> methodInterceptorList = new ArrayList<>();

	public TargetSource getTargetSource() {
		return targetSource;
	}

	public void setTargetSource(TargetSource targetSource) {
		this.targetSource = targetSource;
	}

	public MethodMatcher getMethodMatcher() {
		return methodMatcher;
	}

	public void setMethodMatcher(MethodMatcher methodMatcher) {
		this.methodMatcher = methodMatcher;
	}

	public List<MethodInterceptor> getMethodInterceptorList() {
		return methodInterceptorList;
	}

	public void setMethodInterceptorList(List<MethodInterceptor> methodInterceptorList) {
		this.methodInterceptorList = methodInterceptorList;
	}
}
