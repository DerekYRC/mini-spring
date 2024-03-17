package org.springframework.test.bean;

import org.springframework.beans.factory.annotation.BeforeAnnotation;

/**
 * @author derekyi
 * @date 2021/1/25
 */
//@BeforeAnnotation
public class A {

	private B b;
	@BeforeAnnotation
	public void func(){
		System.out.println("A func has done...");
	}
	public void func2() {
		System.out.println("A func2 has done...");
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}
}
