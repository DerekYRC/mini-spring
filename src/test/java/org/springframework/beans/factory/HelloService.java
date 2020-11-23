package org.springframework.beans.factory;

/**
 * @author derekyi
 * @date 2020/11/22
 */
public class HelloService {

	private String foo;

	private String bar;

	public String getFoo() {
		return foo;
	}

	public void setFoo(String foo) {
		this.foo = foo;
	}

	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

	@Override
	public String toString() {
		return "HelloService{" +
				"foo='" + foo + '\'' +
				", bar='" + bar + '\'' +
				'}';
	}
}
