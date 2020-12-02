package org.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Method;

/**
 * @author derekyi
 * @date 2020/11/22
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
		implements AutowireCapableBeanFactory {

	private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

	@Override
	protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
		return doCreateBean(beanName, beanDefinition);
	}

	protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
		Object bean = null;
		try {
			bean = createBeanInstance(beanDefinition);
			//为bean填充属性
			applyPropertyValues(beanName, bean, beanDefinition);
			//执行bean的初始化方法和BeanPostProcessor的前置和后置处理方法
			initializeBean(beanName, bean, beanDefinition);
		} catch (Exception e) {
			throw new BeansException("Instantiation of bean failed", e);
		}

		//注册有销毁方法的bean
		registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

		if (beanDefinition.isSingleton()) {
			addSingleton(beanName, bean);
		}
		return bean;
	}

	/**
	 * 注册有销毁方法的bean，即bean继承自DisposableBean或有自定义的销毁方法
	 *
	 * @param beanName
	 * @param bean
	 * @param beanDefinition
	 */
	protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
		//只有singleton类型bean会执行销毁方法
		if (beanDefinition.isSingleton()) {
			if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
				registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
			}
		}
	}

	/**
	 * 实例化bean
	 *
	 * @param beanDefinition
	 * @return
	 */
	protected Object createBeanInstance(BeanDefinition beanDefinition) {
		return getInstantiationStrategy().instantiate(beanDefinition);
	}

	/**
	 * 为bean填充属性
	 *
	 * @param bean
	 * @param beanDefinition
	 */
	protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
		try {
			for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
				String name = propertyValue.getName();
				Object value = propertyValue.getValue();
				if (value instanceof BeanReference) {
					// beanA依赖beanB，先实例化beanB
					BeanReference beanReference = (BeanReference) value;
					value = getBean(beanReference.getBeanName());
				}

				//通过反射设置属性
				BeanUtil.setFieldValue(bean, name, value);
			}
		} catch (Exception ex) {
			throw new BeansException("Error setting property values for bean: " + beanName, ex);
		}
	}

	protected Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
		if (bean instanceof BeanFactoryAware) {
			((BeanFactoryAware) bean).setBeanFactory(this);
		}

		//执行BeanPostProcessor的前置处理
		Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

		try {
			invokeInitMethods(beanName, wrappedBean, beanDefinition);
		} catch (Throwable ex) {
			throw new BeansException("Invocation of init method of bean[" + beanName + "] failed", ex);
		}

		//执行BeanPostProcessor的后置处理
		wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
		return wrappedBean;
	}

	@Override
	public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
			throws BeansException {
		Object result = existingBean;
		for (BeanPostProcessor processor : getBeanPostProcessors()) {
			Object current = processor.postProcessBeforeInitialization(result, beanName);
			if (current == null) {
				return result;
			}
			result = current;
		}
		return result;
	}

	@Override
	public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
			throws BeansException {

		Object result = existingBean;
		for (BeanPostProcessor processor : getBeanPostProcessors()) {
			Object current = processor.postProcessAfterInitialization(result, beanName);
			if (current == null) {
				return result;
			}
			result = current;
		}
		return result;
	}

	/**
	 * 执行bean的初始化方法
	 *
	 * @param beanName
	 * @param bean
	 * @param beanDefinition
	 * @throws Throwable
	 */
	protected void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Throwable {
		if (bean instanceof InitializingBean) {
			((InitializingBean) bean).afterPropertiesSet();
		}
		String initMethodName = beanDefinition.getInitMethodName();
		if (StrUtil.isNotEmpty(initMethodName)) {
			Method initMethod = ClassUtil.getPublicMethod(beanDefinition.getBeanClass(), initMethodName);
			if (initMethod == null) {
				throw new BeansException("Could not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
			}
			initMethod.invoke(bean);
		}
	}

	public InstantiationStrategy getInstantiationStrategy() {
		return instantiationStrategy;
	}

	public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
		this.instantiationStrategy = instantiationStrategy;
	}
}
