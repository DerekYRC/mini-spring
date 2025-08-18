package org.springframework.beans.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

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

	/**
	 * 遍历单个 BeanDefinition 的 PropertyValues，替换其中的占位符
	 */
	private void resolvePropertyValues(BeanDefinition beanDefinition, Properties properties) {
		PropertyValues propertyValues = beanDefinition.getPropertyValues();
		for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
			Object value = propertyValue.getValue();
			if (value instanceof String) {
				String strVal = (String) value;
				// 解析字符串中的占位符
				String resolvedValue = parseStringValue(strVal, properties);

				// 如果解析后有变化，则覆盖属性值
				if (!strVal.equals(resolvedValue)) {
					propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), resolvedValue));
				}
			}
		}
	}


	/**
	 * 解析占位符字符串，支持多个占位符和嵌套占位符
	 * <p></p>
	 * 示例输入: "jdbc:${db.url}/${db.name}"
	 * 示例嵌套: "${base.${env}}"
	 */
	private String parseStringValue(String strVal, Properties properties) {
		StringBuilder result = new StringBuilder(strVal.length());
		int index = 0;

		while (index < strVal.length()) {
			// 查找下一个占位符起始位置 "${"
			int startIndex = strVal.indexOf(PLACEHOLDER_PREFIX, index);

			if (startIndex == -1) {
				// 没有更多占位符了，把剩余的字符串直接追加
				result.append(strVal.substring(index));
				break;
			}

			// 将占位符前面的部分追加到结果中
			result.append(strVal, index, startIndex);

			// 查找该占位符对应的结束位置 "}"
			int endIndex = findPlaceholderEndIndex(strVal, startIndex);
			if (endIndex == -1) {
				throw new BeansException("Could not find closing placeholder suffix after index " + startIndex);
			}

			// 提取占位符内容（去掉 ${ 和 }）
			String placeholder = strVal.substring(startIndex + PLACEHOLDER_PREFIX.length(), endIndex);

			// ⚠️递归解析：占位符里面可能还嵌套了其他占位符
			// 例如 placeholder = "base.${env}" → 再次调用 parseStringValue → 得到 "base.dev"
			String resolvedPlaceholder = parseStringValue(placeholder, properties);

			// 根据最终解析结果查找属性值
			String propVal = properties.getProperty(resolvedPlaceholder);
			if (propVal == null) {
				throw new BeansException("Could not resolve placeholder: " + resolvedPlaceholder);
			}

			// 替换占位符为属性值
			result.append(propVal);

			// 移动 index，继续解析后面的内容
			index = endIndex + PLACEHOLDER_SUFFIX.length();
		}

		return result.toString();
	}


	/**
	 * 找到从 startIndex 开始的占位符对应的结束位置 "}"
	 *
	 * ⚠️ 关键点：支持嵌套占位符，不能在遇到第一个 } 就提前结束！
	 * 会跳过嵌套的 `${...}`，直到找到最外层的 `}`
	 */
	private int findPlaceholderEndIndex(String strVal, int startIndex) {
		int index = startIndex + PLACEHOLDER_PREFIX.length(); // 从 "${" 后面开始查
		int withinNestedPlaceholder = 0; // 嵌套计数器

		while (index < strVal.length()) {
			if (strVal.startsWith(PLACEHOLDER_SUFFIX, index)) {
				if (withinNestedPlaceholder > 0) {
					// 当前是内层嵌套的 }，先闭合内层
					withinNestedPlaceholder--;
					index += PLACEHOLDER_SUFFIX.length();
				} else {
					// 当前是最外层的 }，找到结束位置
					return index;
				}
			} else if (strVal.startsWith(PLACEHOLDER_PREFIX, index)) {
				// 遇到新的嵌套，+1
				withinNestedPlaceholder++;
				index += PLACEHOLDER_PREFIX.length();
			} else {
				// 其他普通字符，继续向后扫描
				index++;
			}
		}

		// 没有找到匹配的 }
		return -1;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
