package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * @author derekyi
 * @date 2021/1/30
 */
public interface ObjectFactory<T> {

	T getObject() throws BeansException;
}