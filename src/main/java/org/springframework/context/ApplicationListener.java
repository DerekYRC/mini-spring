package org.springframework.context;

import java.util.EventListener;

/**
 * @author derekyi
 * @date 2020/12/2
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

	void onApplicationEvent(E event);
}