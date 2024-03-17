package org.springframework.aop.support;


import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.core.annotation.MergedAnnotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @Author:sfy
 * @Date: 2024/3/16 - 23:45
 * Description:
 */
public class AnnotationMatchingPointcut implements Pointcut, ClassFilter, MethodMatcher {
    private final Class<? extends Annotation> annotationType;

    public AnnotationMatchingPointcut(Class<? extends Annotation> annotationType) {
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
        //1 类上是否有相关注解，
        // 这步可以删除逻辑合入2，但是在继承关系复杂的情况下，拆开提升一点点运行速度
        MergedAnnotations annotations = MergedAnnotations.from(clazz);
        if (annotations.isPresent(annotationType)) {
            return true;
        }
        //2 类或者父类上是否有相关注解
        annotations = MergedAnnotations.from(clazz, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY);
        if (annotations.isPresent(annotationType)) {
            return true;
        }

        //3 检查类的方法上是否有相关注解。特别的，这里方法上不进行继承
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            MergedAnnotations methodAnnotations = MergedAnnotations.from(method);
            if (methodAnnotations.isPresent(annotationType)) {
                return true;
            }
        }
        return false;
    }

    public boolean matches(Method method) {
        MergedAnnotations annotations = MergedAnnotations.from(method);
        if (annotations.isPresent(annotationType)) {
            return true;
        }
        // 如果注解是直接标注在当前类或者父类及以上，同样匹配成功
        Class<?> clazz = method.getDeclaringClass();
        annotations = MergedAnnotations.from(clazz, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY);
        return annotations.isPresent(annotationType);
    }
    public boolean matches(Method method, Class<?> targetClass) {
        return this.matches(method); //targetClass可以扩充匹配逻辑，暂时不用
    }
}
