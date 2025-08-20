package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.core.convert.support.StringToNumberConverterFactory;
import org.springframework.test.common.StringToBooleanConverter;
import org.springframework.test.common.StringToIntegerConverter;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


/**
 * @author derekyi
 * @date 2021/1/16
 */
public class TypeConversionFirstPartTest {

	@Test
	public void testStringToIntegerConverter() throws Exception {
		StringToIntegerConverter converter = new StringToIntegerConverter();
		Integer num = converter.convert("8888");
		assertThat(num).isEqualTo(8888);
	}

	@Test
	public void testStringToNumberConverterFactory() throws Exception {
		StringToNumberConverterFactory converterFactory = new StringToNumberConverterFactory();

		Converter<String, Integer> stringToIntegerConverter = converterFactory.getConverter(Integer.class);
		Integer intNum = stringToIntegerConverter.convert("8888");
		assertThat(intNum).isEqualTo(8888);

		Converter<String, Long> stringToLongConverter = converterFactory.getConverter(Long.class);
		Long longNum = stringToLongConverter.convert("8888");
		assertThat(longNum).isEqualTo(8888L);

		// Short类型测试
		Converter<String, Short> shortConverter = converterFactory.getConverter(Short.class);
		assertThat(shortConverter.convert("123")).isEqualTo((short) 123);
		assertThat(shortConverter.convert("-123")).isEqualTo((short) -123);

		// Byte类型测试
		Converter<String, Byte> byteConverter = converterFactory.getConverter(Byte.class);
		assertThat(byteConverter.convert("100")).isEqualTo((byte) 100);
		assertThat(byteConverter.convert("-100")).isEqualTo((byte) -100);

		// Float类型测试
		Converter<String, Float> floatConverter = converterFactory.getConverter(Float.class);
		assertThat(floatConverter.convert("123.45")).isEqualTo(123.45f);
		assertThat(floatConverter.convert("-123.45")).isEqualTo(-123.45f);
		assertThat(floatConverter.convert("1.23E+2")).isEqualTo(123.0f);

		// Double类型测试
		Converter<String, Double> doubleConverter = converterFactory.getConverter(Double.class);
		assertThat(doubleConverter.convert("123.456")).isEqualTo(123.456d);
		assertThat(doubleConverter.convert("-123.456")).isEqualTo(-123.456d);
		assertThat(doubleConverter.convert("1.23E+2")).isEqualTo(123.0d);

		// BigInteger类型测试
		Converter<String, BigInteger> bigIntConverter = converterFactory.getConverter(BigInteger.class);
		assertThat(bigIntConverter.convert("12345678901234567890"))
				.isEqualTo(new BigInteger("12345678901234567890"));

		// BigDecimal类型测试
		Converter<String, BigDecimal> bigDecimalConverter = converterFactory.getConverter(BigDecimal.class);
		assertThat(bigDecimalConverter.convert("123.456789012345"))
				.isEqualTo(new BigDecimal("123.456789012345"));

		// null和空字符串测试
		assertThat(stringToIntegerConverter.convert(null)).isNull();
		assertThat(stringToIntegerConverter.convert("")).isNull();
		assertThat(stringToIntegerConverter.convert("   ")).isNull();

		// 空白字符处理测试
		assertThat(stringToIntegerConverter.convert("  123  ")).isEqualTo(123);
		assertThat(stringToIntegerConverter.convert("\t456\t")).isEqualTo(456);

		// 异常情况测试
		assertThatThrownBy(() -> stringToIntegerConverter.convert("abc"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Cannot convert String [abc] to target class [java.lang.Integer]");

		assertThatThrownBy(() -> stringToIntegerConverter.convert("12.34"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Cannot convert String [12.34] to target class [java.lang.Integer]");

		assertThatThrownBy(() -> stringToIntegerConverter.convert("123abc"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Cannot convert String [123abc] to target class [java.lang.Integer]");

		// 数值溢出测试
		assertThatThrownBy(() -> shortConverter.convert("32768")) // Short.MAX_VALUE + 1
				.isInstanceOf(IllegalArgumentException.class);

		assertThatThrownBy(() -> byteConverter.convert("128")) // Byte.MAX_VALUE + 1
				.isInstanceOf(IllegalArgumentException.class);

		// 边界值测试
		assertThat(stringToIntegerConverter.convert(String.valueOf(Integer.MAX_VALUE)))
				.isEqualTo(Integer.MAX_VALUE);
		assertThat(stringToIntegerConverter.convert(String.valueOf(Integer.MIN_VALUE)))
				.isEqualTo(Integer.MIN_VALUE);

		assertThat(stringToLongConverter.convert(String.valueOf(Long.MAX_VALUE)))
				.isEqualTo(Long.MAX_VALUE);
		assertThat(stringToLongConverter.convert(String.valueOf(Long.MIN_VALUE)))
				.isEqualTo(Long.MIN_VALUE);
	}

	@Test
	public void testGenericConverter() throws Exception {
		StringToBooleanConverter converter = new StringToBooleanConverter();

		Boolean flag = (Boolean) converter.convert("true", String.class, Boolean.class);
		assertThat(flag).isTrue();
	}

	@Test
	public void testGenericConversionService() throws Exception {
		GenericConversionService conversionService = new GenericConversionService();
		conversionService.addConverter(new StringToIntegerConverter());

		Integer intNum = conversionService.convert("8888", Integer.class);
		assertThat(conversionService.canConvert(String.class, Integer.class)).isTrue();
		assertThat(intNum).isEqualTo(8888);

		conversionService.addConverterFactory(new StringToNumberConverterFactory());
		assertThat(conversionService.canConvert(String.class, Long.class)).isTrue();
		Long longNum = conversionService.convert("8888", Long.class);
		assertThat(longNum).isEqualTo(8888L);

		conversionService.addConverter(new StringToBooleanConverter());
		assertThat(conversionService.canConvert(String.class, Boolean.class)).isTrue();
		Boolean flag = conversionService.convert("true", Boolean.class);
		assertThat(flag).isTrue();
	}
}