package org.springframework.beans.factory.support;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * @author derekyi
 * @date 2020/11/23
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

	/**
	 * 使用CGLIB动态生成子类
	 *
	 * @param beanDefinition
	 * @return
	 * @throws BeansException
	 */
	@Override
	public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
		Class<?> beanClass = beanDefinition.getBeanClass();
		try {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(beanClass);
			enhancer.setCallback(NoOp.INSTANCE); // 不做任何方法拦截，直接调用父类方法
			return enhancer.create();
		} catch (Exception e) {
			throw new BeansException("Failed to instantiate using CGLIB", e);
		}
	}
}
