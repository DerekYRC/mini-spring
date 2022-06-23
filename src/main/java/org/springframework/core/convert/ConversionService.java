package org.springframework.core.convert;

/**
 * 类型转换抽象接口
 *
 * @author derekyi
 * @date 2021/1/10
 */
public interface ConversionService {

	boolean canConvert(Class<?> sourceType, Class<?> targetType);

	<T> T convert(Object source, Class<T> targetType);
}
