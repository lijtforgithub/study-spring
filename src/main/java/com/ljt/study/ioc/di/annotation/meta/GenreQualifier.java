package com.ljt.study.ioc.di.annotation.meta;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 可以以下面描述的方式来使用JSR 330的@Qualifier注解，用于替换Spring的@Qualifier注解
 * 
 * @author LiJingTang
 * @version 2015年8月28日 下午7:18:21
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface GenreQualifier {

    String value();
}