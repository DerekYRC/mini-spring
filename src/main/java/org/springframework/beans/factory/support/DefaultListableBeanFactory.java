package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * 该类实现了BeanDefinitionRegistry，具有注册BeanDefinition的功能，以及获取BeanDefinition的功能
 * 并且继承了AbstractAutowireCapableBeanFactory，具有创建bean实例的功能
 * AbstractAutowireCapableBeanFactory 继承了 AbstractBeanFactory 所有还就有getBean(), getSingleBean等功能
 *
 * @author derekyi
 * @date 2020/11/22
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

	private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
		beanDefinitionMap.put(beanName, beanDefinition);
	}

	@Override
	protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
		BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
		if (beanDefinition == null) {
			throw new BeansException("No bean named '" + beanName + "' is defined");
		}

		return beanDefinition;
	}
}
