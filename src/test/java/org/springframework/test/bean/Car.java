package org.springframework.test.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 * @author derekyi
 * @date 2020/11/24
 */
@Component
public class Car {

	private int price;

	private LocalDate produceDate;

	private long date;

	@Value("${brand}")
	private String brand;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDate getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(LocalDate produceDate) {
		this.produceDate = produceDate;
	}

	public void init() {
		date = System.currentTimeMillis();
	}

	public void showTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
		System.out.println(date + ":bean create");
	}

	@Override
	public String toString() {
		return "Car{" +
				"price=" + price +
				", produceDate=" + produceDate +
				", brand='" + brand + '\'' +
				'}';
	}
}
