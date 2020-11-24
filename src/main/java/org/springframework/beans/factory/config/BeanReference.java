package org.springframework.beans.factory.config;

/**
 * 一个bean对另一个bean的引用
 *
 * @author derekyi
 * @date 2020/11/24
 */
public class BeanReference {

	private final String beanName;


	public BeanReference(String beanName) {
		this.beanName = beanName;
	}

	public String getBeanName() {
		return beanName;
	}
}
