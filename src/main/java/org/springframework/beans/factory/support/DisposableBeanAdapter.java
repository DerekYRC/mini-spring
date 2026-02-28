package org.springframework.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author derekyi
 * @date 2020/11/29
 */
public class DisposableBeanAdapter implements DisposableBean {

	private final Object bean;

	private final String beanName;

	private final String destroyMethodName;

	private final Class<?> beanClass;

	public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
		this.bean = bean;
		this.beanName = beanName;
		this.destroyMethodName = beanDefinition.getDestroyMethodName();
		// 保存原始类（而不是代理类），用于查找方法
		this.beanClass = beanDefinition.getBeanClass();
	}

	@Override
	public void destroy() throws Exception {
		if (bean instanceof DisposableBean) {
			((DisposableBean) bean).destroy();
		}

		//避免同时继承自DisposableBean，且自定义方法与DisposableBean方法同名，销毁方法执行两次的情况
		if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
			//执行自定义方法
			// 优先从原始类（而非代理类）查找方法
			Class<?> targetClass = beanClass != null ? beanClass : bean.getClass();
			Method destroyMethod = ClassUtil.getPublicMethod(targetClass, destroyMethodName);
			if (destroyMethod == null && targetClass != bean.getClass()) {
				// 如果原始类找不到，尝试从代理类查找
				destroyMethod = ClassUtil.getPublicMethod(bean.getClass(), destroyMethodName);
			}
			if (destroyMethod == null) {
				throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
			}
			destroyMethod.invoke(bean);
		}
	}
}
