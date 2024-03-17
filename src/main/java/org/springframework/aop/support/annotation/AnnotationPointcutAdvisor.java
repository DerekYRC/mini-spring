package org.springframework.aop.support.annotation;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.AnnotationMatchingPointcut;

import java.lang.annotation.Annotation;

/**
 * @Author:sfy
 * @Date: 2024/3/17 - 20:05
 * Description: 基于注解的advisor
 */
public class AnnotationPointcutAdvisor implements PointcutAdvisor {
    private AnnotationMatchingPointcut pointcut;
    private Advice advice;
    private Class<? extends Annotation> annotation;
    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (pointcut == null) {
            pointcut = new AnnotationMatchingPointcut(annotation);
        }
        return pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setAnnotation(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }
}
