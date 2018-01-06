package com.jointem.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Atuhor wangwei
 * Date 2017/5/17
 * Time 上午9:01
 * Description
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface OtherObjectAnnotation {
    boolean canBeNull() default false;

    String message() default "";
}
