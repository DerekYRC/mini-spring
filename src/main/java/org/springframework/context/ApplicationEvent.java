package org.springframework.context;

import java.util.EventObject;

/**
 * @author derekyi
 * @date 2020/12/2
 */
public abstract class ApplicationEvent extends EventObject {

	public ApplicationEvent(Object source) {
		super(source);
	}
}
