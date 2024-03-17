package org.springframework.core.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;

/**
 * @Author:sfy
 * @Date: 2024/3/17 - 16:12
 * Description:
 */
public interface MergedAnnotations {
    static MergedAnnotations from(AnnotatedElement annotatedElement) {
        return from(annotatedElement, SearchStrategy.DIRECT);
    };

    static MergedAnnotations from(AnnotatedElement annotatedElement, SearchStrategy searchStrategy) {
        return DefaultMergedAnnotations.from(annotatedElement, searchStrategy);
    };

    boolean isPresent(Class<? extends Annotation> annotationType);
    enum SearchStrategy {
        // 只搜索当前类
        DIRECT,
        // 在使用TYPE_HIERARCHY进行注解搜索时，会搜索类本身所标注的注解，以及其父类和接口所标注的注解
        TYPE_HIERARCHY
    }
}
