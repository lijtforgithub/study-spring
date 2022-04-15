package com.ljt.study.reqlog.retry;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jtli3
 * @date 2022-02-23 10:35
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RetryRC {

    RetryEnum value() default RetryEnum.RETRY_SAVE;

    /**
     * 接口说明
     */
    String desc() default "";

}
