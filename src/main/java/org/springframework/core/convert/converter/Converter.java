package org.springframework.core.convert.converter;

/**
 * 类型转换抽象接口
 *
 * @author derekyi
 * @date 2021/1/10
 */
public interface Converter<S, T> {

	/**
	 * 类型转换
	 */
	T convert(S source);
}
