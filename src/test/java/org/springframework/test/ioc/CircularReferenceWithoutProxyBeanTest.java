package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.*;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author derekyi
 * @date 2021/1/25
 */
public class CircularReferenceWithoutProxyBeanTest {

	@Test
	public void testCircularReference() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:circular-reference-without-proxy-bean.xml");
		A a = applicationContext.getBean("a", A.class);
		B b = applicationContext.getBean("b", B.class);
		A a1 = applicationContext.getBean("a", A.class);
		A a2 = applicationContext.getBean("a", A.class);
		assertThat(a.getB() == b).isFalse();
		assertThat(a1.getB() == a2.getB()).isFalse();
		assertThat(a1 == a2).isFalse();
	}
}
