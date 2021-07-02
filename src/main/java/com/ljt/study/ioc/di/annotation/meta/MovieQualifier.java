package com.ljt.study.ioc.di.annotation.meta;

import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 可以定义自定义的限定符注解来接受命名属性，除了或替代简单的value属性。
 * 如果多个属性值之后在要不自动装配的字段或参数上来指定，那么要考虑bean 的定义必须匹配自动装备候选者的所有属性值
 * 
 * @author LiJingTang
 * @version 2015年8月28日 下午7:16:49
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface MovieQualifier {

	String genre();

    Format format();
}
