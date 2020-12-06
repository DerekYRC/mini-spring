package org.springframework.aop.framework;

import org.springframework.aop.AdvisedSupport;

/**
 * @author derekyi
 * @date 2020/12/6
 */
public class ProxyFactory {

	private AdvisedSupport advisedSupport;

	public ProxyFactory(AdvisedSupport advisedSupport) {
		this.advisedSupport = advisedSupport;
	}

	public Object getProxy() {
		return createAopProxy().getProxy();
	}

	private AopProxy createAopProxy() {
		if (advisedSupport.isProxyTargetClass()) {
			return new CglibAopProxy(advisedSupport);
		}

		return new JdkDynamicAopProxy(advisedSupport);
	}
}
