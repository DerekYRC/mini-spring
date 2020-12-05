package org.springframework.test.bean;

/**
 * @author derekyi
 * @date 2020/11/24
 */
public class Car {

	private String brand;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Car{" +
				"brand='" + brand + '\'' +
				'}';
	}
}
