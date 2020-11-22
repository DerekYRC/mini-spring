package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @author derekyi
 * @date 2020/11/22
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

	@Override
	protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
		return doCreateBean(beanName, beanDefinition);
	}

	protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
		Class beanClass = beanDefinition.getBeanClass();
		Object bean = null;
		try {
			bean = beanClass.newInstance();
		} catch (Exception e) {
			throw new BeansException("Instantiation of bean failed", e);
		}

		addSingleton(beanName, bean);
		return bean;
	}
}
