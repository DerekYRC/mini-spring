package org.springframework.beans.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * @author derekyi
 * @date 2020/12/13
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

	public static final String PLACEHOLDER_PREFIX = "${";

	public static final String PLACEHOLDER_SUFFIX = "}";

	private String location;

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		//加载属性配置文件
		Properties properties = loadProperties();

		//属性值替换占位符
		processProperties(beanFactory, properties);

		//往容器中添加字符解析器，供解析@Value注解使用
		StringValueResolver valueResolver = new PlaceholderResolvingStringValueResolver(properties);
		beanFactory.addEmbeddedValueResolver(valueResolver);
	}

	/**
	 * 加载属性配置文件
	 *
	 * @return
	 */
	private Properties loadProperties() {
		try {
			DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
			Resource resource = resourceLoader.getResource(location);
			Properties properties = new Properties();
			properties.load(resource.getInputStream());
			return properties;
		} catch (IOException e) {
			throw new BeansException("Could not load properties", e);
		}
	}

	/**
	 * 属性值替换占位符
	 *
	 * @param beanFactory
	 * @param properties
	 * @throws BeansException
	 */
	private void processProperties(ConfigurableListableBeanFactory beanFactory, Properties properties) throws BeansException {
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		for (String beanName : beanDefinitionNames) {
			BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
			resolvePropertyValues(beanDefinition, properties);
		}
	}

	private void resolvePropertyValues(BeanDefinition beanDefinition, Properties properties) {
		PropertyValues propertyValues = beanDefinition.getPropertyValues();
		for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
			Object value = propertyValue.getValue();
			if (value instanceof String) {
				value = resolvePlaceholder((String) value, properties);
				propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), value));
			}
		}
	}

	private String resolvePlaceholder(String value, Properties properties) {
		//TODO 仅简单支持一个占位符的格式
		String strVal = value;
		StringBuffer buf = new StringBuffer(strVal);
		int startIndex = strVal.indexOf(PLACEHOLDER_PREFIX);
		int endIndex = strVal.indexOf(PLACEHOLDER_SUFFIX);
		if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
			String propKey = strVal.substring(startIndex + 2, endIndex);
			String propVal = properties.getProperty(propKey);
			buf.replace(startIndex, endIndex + 1, propVal);
		}
		return buf.toString();
	}

	public void setLocation(String location) {
		this.location = location;
	}

	private class PlaceholderResolvingStringValueResolver implements StringValueResolver {

		private final Properties properties;

		public PlaceholderResolvingStringValueResolver(Properties properties) {
			this.properties = properties;
		}

		public String resolveStringValue(String strVal) throws BeansException {
			return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strVal, properties);
		}
	}
}
