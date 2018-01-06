package com.jointem.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Atuhor wangwei
 * Date 2017/5/17
 * Time 上午9:00
 * Description
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ListParamAnnonation {
    boolean canBeNull() default false;

    int maxSize() default -1;

    int minSize() default -1;

    String message() default "";
}

