package org.springframework.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;

import org.springframework.aop.AdvisedSupport;
import org.springframework.aop.Advisor;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationPointcutAdvisor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zqc
 * @date 2022/12/17
 */
public class DefaultAdvisorChainFactory implements AdvisorChainFactory {

	@Override
	public List<Object> getInterceptorsAndDynamicInterceptionAdvice(AdvisedSupport config, Method method, Class<?> targetClass) {
		Advisor[] advisors = config.getAdvisors().toArray(new Advisor[0]);
		List<Object> interceptorList = new ArrayList<>(advisors.length);
		Class<?> actualClass = (targetClass != null ? targetClass : method.getDeclaringClass());
		for (Advisor advisor : advisors) {
			if (advisor instanceof PointcutAdvisor) {
//				if (advisor instanceof AnnotationPointcutAdvisor) {
//					AnnotationPointcutAdvisor pointcutAdvisor = (AnnotationPointcutAdvisor) advisor;
//					// 注解修饰类或者直接修饰方法，两种情况任一即可匹配成功
//					boolean match = pointcutAdvisor.getPointcut().getMethodMatcher().matches(method,actualClass) ||
//							pointcutAdvisor.getPointcut().getClassFilter().matches(actualClass);
//					if (match) {
//						MethodInterceptor interceptor = (MethodInterceptor) advisor.getAdvice();
//						interceptorList.add(interceptor);
//					}
//				} else if (advisor instanceof AspectJExpressionPointcutAdvisor){
					// Add it conditionally.
					PointcutAdvisor pointcutAdvisor = (PointcutAdvisor) advisor;
					// 校验当前Advisor是否适用于当前对象
					if (pointcutAdvisor.getPointcut().getClassFilter().matches(actualClass)) {
						MethodMatcher mm = pointcutAdvisor.getPointcut().getMethodMatcher();
						boolean match;
						// 校验Advisor是否应用到当前方法上
						match = mm.matches(method, actualClass);
						if (match) {
							MethodInterceptor interceptor = (MethodInterceptor) advisor.getAdvice();
							interceptorList.add(interceptor);
						}
//					}
				} else {
					throw new IllegalArgumentException("unsupported PointcutAdvisor : " + advisor.getClass().getName());
				}
			}
		}
		return interceptorList;
	}
}
