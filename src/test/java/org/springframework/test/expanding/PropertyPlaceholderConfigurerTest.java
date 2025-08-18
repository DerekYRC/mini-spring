package org.springframework.test.expanding;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.bean.Car;
import org.springframework.test.bean.ServerConfig;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author derekyi
 * @date 2020/12/13
 */
public class PropertyPlaceholderConfigurerTest {

	@Test
	public void test() throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:property-placeholder-configurer.xml");
		ServerConfig serverConfig = applicationContext.getBean("serverConfig", ServerConfig.class);
		assertThat(serverConfig.getUrl()).isEqualTo("http://localhost:8080");
		assertThat(serverConfig.getPath()).isEqualTo("/api/dev");
		assertThat(serverConfig.getLocation()).isEqualTo("China");
	}
}
