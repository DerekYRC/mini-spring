package org.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * @author derekyi
 * @date 2020/12/26
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

	String value() default "singleton";
}
