package org.springframework.aop.framework.autoproxy;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.aopalliance.aop.Advice;

import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author derekyi
 * @date 2020/12/6
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

	private DefaultListableBeanFactory beanFactory;

	private Set<Object> earlyProxyReferences = new HashSet<>();

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (!earlyProxyReferences.contains(beanName)) {
			return wrapIfNecessary(bean, beanName);
		}

		return bean;
	}

	@Override
	public Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
		earlyProxyReferences.add(beanName);
		return wrapIfNecessary(bean, beanName);
	}

	protected Object wrapIfNecessary(Object bean, String beanName) {
		//避免死循环
		if (isInfrastructureClass(bean.getClass())) {
			return bean;
		}

		Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class)
				.values();
		try {
			ProxyFactory proxyFactory = new ProxyFactory();
			for (AspectJExpressionPointcutAdvisor advisor : advisors) {
				ClassFilter classFilter = advisor.getPointcut().getClassFilter();
				if (classFilter.matches(bean.getClass())) {
					TargetSource targetSource = new TargetSource(bean);
					proxyFactory.setTargetSource(targetSource);
					proxyFactory.addAdvisor(advisor);
					proxyFactory.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
				}
			}
			if (!proxyFactory.getAdvisors().isEmpty()) {
				return proxyFactory.getProxy();
			}
		} catch (Exception ex) {
			throw new BeansException("Error create proxy bean for: " + beanName, ex);
		}
		return bean;
	}

	private boolean isInfrastructureClass(Class<?> beanClass) {
		return Advice.class.isAssignableFrom(beanClass)
				|| Pointcut.class.isAssignableFrom(beanClass)
				|| Advisor.class.isAssignableFrom(beanClass);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = (DefaultListableBeanFactory) beanFactory;
	}

	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		return null;
	}

	@Override
	public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
		return true;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
		return pvs;
	}
}
