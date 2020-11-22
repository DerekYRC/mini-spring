package org.springframework.beans.factory.config;

/**
 * BeanDefinition实例保存bean的信息，包括class类型、方法构造参数、是否为单例等，此处简化只包含class类型
 *
 * @author derekyi
 * @date 2020/11/22
 */
public class BeanDefinition {

	private Class beanClass;

	public BeanDefinition(Class beanClass) {
		this.beanClass = beanClass;
	}

	public Class getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}
}
