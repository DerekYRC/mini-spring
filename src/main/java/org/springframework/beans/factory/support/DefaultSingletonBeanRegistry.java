package org.springframework.beans.factory.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;

/**
 * @author derekyi
 * @date 2020/11/22
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

	 /**
	  * 一级缓存
	  */
	private Map<String, Object> singletonObjects = new HashMap<>();

	 /**
	  * 二级缓存
	  */
	private Map<String, Object> earlySingletonObjects = new HashMap<>();

	 /**
	  * 三级缓存
	  */
	private Map<String, ObjectFactory<?>> singletonFactories = new HashMap<String, ObjectFactory<?>>();

	private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

	@Override
	public Object getSingleton(String beanName) {
		return getSingleton(beanName,true);
	}

	public Object getSingleton(String beanName,boolean allowEarlyReference) {
		Object singletonObject = singletonObjects.get(beanName);
		if (singletonObject == null) {
			singletonObject = earlySingletonObjects.get(beanName);
			//不是所有的getSingleton()都要去三级缓存里面查！
			if (singletonObject == null && allowEarlyReference) {
				ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
				if (singletonFactory != null) {
					singletonObject = singletonFactory.getObject();
					//从三级缓存放进二级缓存
					earlySingletonObjects.put(beanName, singletonObject);
					singletonFactories.remove(beanName);
				}
			}
		}
		return singletonObject;
	}

	@Override
	public void addSingleton(String beanName, Object singletonObject) {
		singletonObjects.put(beanName, singletonObject); // 1
		earlySingletonObjects.remove(beanName); // 2
		singletonFactories.remove(beanName); // 3
	}

	protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
		singletonFactories.put(beanName, singletonFactory);
	}

	public void registerDisposableBean(String beanName, DisposableBean bean) {
		disposableBeans.put(beanName, bean);
	}

	public void destroySingletons() {
		ArrayList<String> beanNames = new ArrayList<>(disposableBeans.keySet());
		for (String beanName : beanNames) {
			DisposableBean disposableBean = disposableBeans.remove(beanName);
			try {
				disposableBean.destroy();
			} catch (Exception e) {
				throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
			}
		}
	}
}
