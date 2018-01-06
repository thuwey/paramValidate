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
public @interface NumberParamAnnonation {
    boolean canBeNull() default false;

    int minNum() default -1;

    int maxNum() default -1;

    String message() default "";
}
