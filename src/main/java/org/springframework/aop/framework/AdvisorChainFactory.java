package org.springframework.aop.framework;

import org.springframework.aop.AdvisedSupport;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zqc
 * @date 2022/12/16
 */
public interface AdvisorChainFactory {


    List<Object> getInterceptorsAndDynamicInterceptionAdvice(AdvisedSupport config, Method method, Class<?> targetClass);

}
