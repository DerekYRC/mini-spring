package org.springframework.aop;

/**
 * @author derekyi
 * @date 2020/12/6
 */
public interface PointcutAdvisor extends Advisor {

	Pointcut getPointcut();
}
