package org.springframework.aop.annotation_scan;


import com.sun.scenario.effect.Merge;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author:sfy
 * @Date: 2024/3/16 - 23:45
 * Description:
 */
public class SimpleAnnotationPointcut implements Pointcut, ClassFilter, MethodMatcher {
    private final Class<? extends Annotation> annotationType;

    public SimpleAnnotationPointcut(Class<? extends Annotation> annotationType) {
        this.annotationType = annotationType;
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

    @Override
    public boolean matches(Class<?> clazz) {
        return false;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        //1 方法上是否有相关注解
        MergedAnnotations annotations = MergedAnnotations.from(method);
        if (annotations.isPresent(annotationType)) {
            return true;
        }
        //2 类或者父类上是否有相关注解
        annotations = MergedAnnotations.from(method, MergedAnnotations.SearchStrategy.TYPE_HIERARCH);
        if (annotations.isPresent(annotationType)) {
            return true;
        }
        return false;
    }
}
