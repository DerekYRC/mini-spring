package org.springframework.beans.factory.config;

import org.springframework.beans.factory.BeanFactory;

/**
 * @author derekyi
 * @date 2020/11/28
 */
public interface ConfigurableBeanFactory extends BeanFactory, SingletonBeanRegistry {

	/**
	 * @param beanPostProcessor
	 */
	void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
