package org.springframework.test.common;

import org.springframework.core.convert.converter.Converter;

/**
 * @author derekyi
 * @date 2021/1/16
 */
public class StringToIntegerConverter implements Converter<String, Integer> {
	@Override
	public Integer convert(String source) {
		return Integer.valueOf(source);
	}
}
