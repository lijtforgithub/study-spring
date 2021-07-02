package com.ljt.study.ioc.di.annotation.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author LiJingTang
 * @version 2015年8月27日 下午6:40:27
 */
public class QualifierBean {

	private String data;
	@Autowired
	@Qualifier("secondPrimaryBean") // JSR 330的@Qualifier注解仅仅能作为元注解来用，而不像Spring的@Qualifier可以使用字符串属性来在多个注入的候选者之间区别，并可以放在注解，类型，字段，方法，构造方法和参数中。 和@Autowired一起用
	private PrimaryBean primaryBean;
	
	public QualifierBean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}
	
	public String getData() {
		return data;
	}
	public PrimaryBean getPrimaryBean() {
		return primaryBean;
	}
}
