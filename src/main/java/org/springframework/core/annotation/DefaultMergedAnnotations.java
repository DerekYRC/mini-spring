package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:sfy
 * @Date: 2024/3/17 - 16:15
 * Description:
 */
public class DefaultMergedAnnotations implements MergedAnnotations{
    private static Annotation[] annotations;

    public DefaultMergedAnnotations(Annotation[] annotations) {
        DefaultMergedAnnotations.annotations = annotations;
    }

    public static MergedAnnotations from(AnnotatedElement annotatedElement, SearchStrategy searchStrategy) {
        // 获取给定元素的所有直接注解
        if (searchStrategy.equals(SearchStrategy.DIRECT)) {
            annotations = annotatedElement.getDeclaredAnnotations();
        }
        /* 获取给定元素及其继承父类和接口所直接继承的所有注解
         * 这里暂时只对类上标注的注解进行继承，对于方法上标注的注解不进行继承，这块是很灵活的
         */
        else if (searchStrategy.equals(SearchStrategy.TYPE_HIERARCHY)) {
            // 获取给定元素本身的所有注解
            List<Annotation> annotationList = new ArrayList<>(Arrays.asList(annotatedElement.getDeclaredAnnotations()));

            // 获取给定元素的父类的所有注解
            if (annotatedElement instanceof Class) {
                Class<?> clazz = (Class<?>) annotatedElement;
                annotationList.addAll(getAnnotationsFromSuperclass(clazz));
            }

            // 获取给定元素实现的所有接口的注解
            annotationList.addAll(getAnnotationsFromImplementedInterfaces(annotatedElement));

            annotations = annotationList.toArray(new Annotation[0]);
        }
        else {
            throw new IllegalArgumentException("Unsupported search strategy: " + searchStrategy);
        }

        return new DefaultMergedAnnotations(annotations);
    }

    public boolean isPresent(Class<? extends Annotation> annotationType) {
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(annotationType)) {
                return true;
            }
        }
        return false;
    }

    // 获取给定类的父类的所有注解
    private static List<Annotation> getAnnotationsFromSuperclass(Class<?> clazz) {
        List<Annotation> annotations = new ArrayList<>();
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            annotations.addAll(Arrays.asList(superclass.getDeclaredAnnotations()));
            annotations.addAll(getAnnotationsFromSuperclass(superclass)); // 递归获取父类的注解
        }
        return annotations;
    }

    private static List<Annotation> getAnnotationsFromImplementedInterfaces(AnnotatedElement annotatedElement) {
        List<Annotation> annotations = new ArrayList<>();
        if (annotatedElement instanceof Class) {
            Class<?>[] interfaces = ((Class<?>) annotatedElement).getInterfaces();
            for (Class<?> interfaze : interfaces) {
                annotations.addAll(Arrays.asList(interfaze.getDeclaredAnnotations()));
                annotations.addAll(getAnnotationsFromImplementedInterfaces(interfaze)); // 递归获取接口的注解
            }
        }
        return annotations;
    }
}
