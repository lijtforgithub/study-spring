package com.ljt.study.ioc.bean.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author LiJingTang
 * @version 2015年8月28日 下午11:18:49
 */

@Configuration
@ComponentScan(basePackages="com.ljt.study.ioc.bean.annotation.component",
	useDefaultFilters=true,
		excludeFilters=@Filter(type=FilterType.REGEX, pattern = ".*.other.*"),
		includeFilters=@Filter(Controller.class)) // ?暂未发现相当于XML配置annotation-config="false"的注解 关闭自动注入(查看@ComponentScan类说明)
class ComponentConfig {
	
}