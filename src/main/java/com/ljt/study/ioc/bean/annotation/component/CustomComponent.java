package com.ljt.study.ioc.bean.annotation.component;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author LiJingTang
 * @version 2015年8月28日 下午11:15:19
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface CustomComponent {

	String value() default "";
}
