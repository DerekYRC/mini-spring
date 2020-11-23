package org.springframework.beans.factory;

import org.junit.Test;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author derekyi
 * @date 2020/11/22
 */
public class BeanFactoryTest {

	@Test
	public void testBeanFactory() throws Exception {
		DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
		PropertyValues propertyValues = new PropertyValues();
		propertyValues.addPropertyValue(new PropertyValue("foo", "hello"));
		propertyValues.addPropertyValue(new PropertyValue("bar", "world"));
		BeanDefinition beanDefinition = new BeanDefinition(HelloService.class, propertyValues);
		beanFactory.registerBeanDefinition("helloService", beanDefinition);

		HelloService helloService = (HelloService) beanFactory.getBean("helloService");
		System.out.println(helloService.toString());
		assertThat(helloService.getFoo()).isEqualTo("hello");
		assertThat(helloService.getBar()).isEqualTo("world");
	}
}

