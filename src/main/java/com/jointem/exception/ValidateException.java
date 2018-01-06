package com.jointem.exception;

/**
 * Atuhor wangwei
 * Date 2017/5/17
 * Time 上午9:03
 * Description
 **/
public class ValidateException extends RuntimeException {

    public ValidateException(String messge) {
        super(messge);
    }

    public ValidateException(String messge, Throwable throwable) {
        super(messge, throwable);
    }

    public Throwable fillInStackTrace() {
        return this;
    }
}