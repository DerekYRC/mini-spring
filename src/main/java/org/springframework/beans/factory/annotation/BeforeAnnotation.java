package org.springframework.beans.factory.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author:sfy
 * @Date: 2024/3/17 - 17:10
 * Description: AnnotationPointcutAdvisor = Pointcut(Annotation) + Advice
 * 被@BeforeAnnotation注解修饰的类或方法，可应用自定义的增强
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
public @interface BeforeAnnotation {
}
