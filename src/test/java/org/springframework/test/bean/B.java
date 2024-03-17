package org.springframework.test.bean;

import org.springframework.beans.factory.annotation.AfterAnnotation;

/**
 * @author derekyi
 * @date 2021/1/25
 */
@AfterAnnotation
public class B {

	public void func() {
		System.out.println("B func has done...");
	}
	private A a;

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}

	public void func2() {
		System.out.println("B func2 has done...");
	}
}
