package org.springframework.beans.factory.xml;


import cn.hutool.core.util.StrUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 读取配置在xml文件中的bean定义信息
 *
 * @author derekyi
 * @date 2020/11/26
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

	public static final String BEAN_ELEMENT = "bean";
	public static final String PROPERTY_ELEMENT = "property";
	public static final String ID_ATTRIBUTE = "id";
	public static final String NAME_ATTRIBUTE = "name";
	public static final String CLASS_ATTRIBUTE = "class";
	public static final String VALUE_ATTRIBUTE = "value";
	public static final String REF_ATTRIBUTE = "ref";
	public static final String INIT_METHOD_ATTRIBUTE = "init-method";
	public static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";

	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
		super(registry);
	}

	public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
		super(registry, resourceLoader);
	}

	@Override
	public void loadBeanDefinitions(String location) throws BeansException {
		ResourceLoader resourceLoader = getResourceLoader();
		Resource resource = resourceLoader.getResource(location);
		loadBeanDefinitions(resource);
	}

	@Override
	public void loadBeanDefinitions(Resource resource) throws BeansException {
		try {
			InputStream inputStream = resource.getInputStream();
			try {
				doLoadBeanDefinitions(inputStream);
			} finally {
				inputStream.close();
			}
		} catch (IOException | DocumentException ex) {
			throw new BeansException("IOException parsing XML document from " + resource, ex);
		}
	}

	protected void doLoadBeanDefinitions(InputStream inputStream) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);

		Element beans = document.getRootElement();
		List<Element> beanList = beans.elements(BEAN_ELEMENT);
		for (Element bean : beanList) {
			String beanId = bean.attributeValue(ID_ATTRIBUTE);
			String beanName = bean.attributeValue(NAME_ATTRIBUTE);
			String className = bean.attributeValue(CLASS_ATTRIBUTE);
			String initMethodName = bean.attributeValue(INIT_METHOD_ATTRIBUTE);
			String destroyMethodName = bean.attributeValue(DESTROY_METHOD_ATTRIBUTE);

			Class<?> clazz;
			try {
				clazz = Class.forName(className);
			} catch (ClassNotFoundException e) {
				throw new BeansException("Cannot find class [" + className + "]");
			}
			//id优先于name
			beanName = StrUtil.isNotEmpty(beanId) ? beanId : beanName;
			if (StrUtil.isEmpty(beanName)) {
				//如果id和name都为空，将类名的第一个字母转为小写后作为bean的名称
				beanName = StrUtil.lowerFirst(clazz.getSimpleName());
			}

			BeanDefinition beanDefinition = new BeanDefinition(clazz);
			beanDefinition.setInitMethodName(initMethodName);
			beanDefinition.setDestroyMethodName(destroyMethodName);

			List<Element> propertyList = bean.elements(PROPERTY_ELEMENT);
			for (Element property : propertyList) {
				String propertyNameAttribute = property.attributeValue(NAME_ATTRIBUTE);
				String propertyValueAttribute = property.attributeValue(VALUE_ATTRIBUTE);
				String propertyRefAttribute = property.attributeValue(REF_ATTRIBUTE);

				if (StrUtil.isEmpty(propertyNameAttribute)) {
					throw new BeansException("The name attribute cannot be null or empty");
				}

				Object value = propertyValueAttribute;
				if (StrUtil.isNotEmpty(propertyRefAttribute)) {
					value = new BeanReference(propertyRefAttribute);
				}
				PropertyValue propertyValue = new PropertyValue(propertyNameAttribute, value);
				beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
			}
			if (getRegistry().containsBeanDefinition(beanName)) {
				//beanName不能重名
				throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
			}
			//注册BeanDefinition
			getRegistry().registerBeanDefinition(beanName, beanDefinition);
		}
	}
}
