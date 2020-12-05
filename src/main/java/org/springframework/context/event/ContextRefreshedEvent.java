package org.springframework.context.event;

import org.springframework.context.ApplicationContext;

/**
 * @author derekyi
 * @date 2020/12/2
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {

	public ContextRefreshedEvent(ApplicationContext source) {
		super(source);
	}
}
