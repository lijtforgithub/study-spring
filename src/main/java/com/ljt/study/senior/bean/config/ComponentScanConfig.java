package com.ljt.study.senior.bean.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;

import com.ljt.study.senior.bean.custom.CustomTypeFilter;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @author LiJingTang
 * @version 2019年3月29日 下午4:06:24
 */
@Configuration
@ComponentScan(basePackages = "com.ljt.study.senior.bean.entity", useDefaultFilters = false, 
	includeFilters = @Filter(type = FilterType.CUSTOM, classes = CustomTypeFilter.class))
//@ComponentScans
public class ComponentScanConfig {
	
}
