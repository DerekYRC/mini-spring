package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.A;
import org.springframework.test.bean.B;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 * @author derekyi
 * @date 2021/1/30
 */
public class CircularReferenceWithProxyBeanTest {

	@Test
	public void testCircularReference() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:circular-reference-with-proxy-bean.xml");
		A a = applicationContext.getBean("a", A.class);
		B b = applicationContext.getBean("b", B.class);

		//增加二级缓存不能解决有代理对象时的循环依赖。
		//a被代理，放进二级缓存earlySingletonObjects中的是实例化后的A，而放进一级缓存singletonObjects中的是被代理后的A，实例化b时从earlySingletonObjects获取a，所以b.getA() != a
		assertThat(b.getA() != a).isTrue();
	}
}
