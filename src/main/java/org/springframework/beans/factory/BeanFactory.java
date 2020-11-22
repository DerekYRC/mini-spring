package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * bean容器
 *
 * @author derekyi
 * @date 2020/11/22
 */
public interface BeanFactory {

	/**
	 * 获取bean
	 *
	 * @param name
	 * @return
	 * @throws BeansException bean不存在时
	 */
	Object getBean(String name) throws BeansException;
}
