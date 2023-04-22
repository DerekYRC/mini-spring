package org.springframework.beans;

/**
 * @author derekyi
 * @date 2020/11/22
 * bean异常
 */
public class BeansException extends RuntimeException {

	public BeansException(String msg) {
		super(msg);
	}

	public BeansException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
