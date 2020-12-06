package org.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @author derekyi
 * @date 2020/12/6
 */
public interface Advisor {

	Advice getAdvice();

}
