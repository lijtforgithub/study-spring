package com.ljt.study.ioc.di.annotation;

import com.ljt.study.ioc.di.annotation.qualifier.PrimaryBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author LiJingTang
 * @version 2015年8月28日 下午6:26:14
 */
public class ResourceBean {
	
	@Resource(name="bean1") // JSR 250 如果没有配置name则按照字段属性名查找对应的bean
	private PrimaryBean bean;

	public ResourceBean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}

	public PrimaryBean getBean() {
		return bean;
	}
	
	/**
	 * CommonAnnotationBeanPostProcessor不但能识别@Resource注解，而且还能识别JSR-250生命周期注解
	 */
	
	@PostConstruct
	public void init() {
		System.out.println("	" + this.getClass().getName() + "调用init()方法");
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("	" + this.getClass().getName() + "调用destroy()方法");
	}

}
