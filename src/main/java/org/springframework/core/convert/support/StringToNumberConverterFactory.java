package org.springframework.core.convert.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author derekyi
 * @date 2021/1/10
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {

	@Override
	public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
		return new StringToNumber<T>(targetType);
	}

	private static final class StringToNumber<T extends Number> implements Converter<String, T> {

		private final Class<T> targetType;

		public StringToNumber(Class<T> targetType) {
			this.targetType = targetType;
		}

		@Override
		public T convert(String source) {
			// 处理null和空字符串
			if (source == null || source.isEmpty()) {
				return null;
			}

			// 去除首尾空白字符
			source = source.trim();
			if (source.isEmpty()) {
				return null;
			}

			try {
				// Integer类型转换
				if (targetType.equals(Integer.class)) {
					return (T) Integer.valueOf(source);
				}
				// Long类型转换
				else if (targetType.equals(Long.class)) {
					return (T) Long.valueOf(source);
				}
				// Short类型转换
				else if (targetType.equals(Short.class)) {
					return (T) Short.valueOf(source);
				}
				// Byte类型转换
				else if (targetType.equals(Byte.class)) {
					return (T) Byte.valueOf(source);
				}
				// Float类型转换
				else if (targetType.equals(Float.class)) {
					return (T) Float.valueOf(source);
				}
				// Double类型转换
				else if (targetType.equals(Double.class)) {
					return (T) Double.valueOf(source);
				}
				// BigInteger类型转换
				else if (targetType.equals(BigInteger.class)) {
					return (T) new BigInteger(source);
				}
				// BigDecimal类型转换
				else if (targetType.equals(BigDecimal.class)) {
					return (T) new BigDecimal(source);
				}
				// 不支持的数字类型
				else {
					throw new IllegalArgumentException(
							"Cannot convert String [" + source + "] to target class [" + targetType.getName() + "]");
				}
			} catch (NumberFormatException ex) {
				throw new IllegalArgumentException(
						"Cannot convert String [" + source + "] to target class [" + targetType.getName() + "]", ex);
			}
		}
	}

}