package org.springframework.beans;

/**
 * bean属性信息
 *
 * @author derekyi
 * @date 2020/11/23
 */
public class PropertyValue {

	private final String name;

	private final String value;

	public PropertyValue(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}
