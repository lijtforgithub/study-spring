package com.ljt.study.ioc.di.annotation;

import com.ljt.study.ioc.bean.xml.Bean;
import com.ljt.study.ioc.bean.xml.extend.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Resource;
import javax.inject.Inject;


/**
 * @author LiJingTang
 * @version 2015年8月26日 下午6:31:15
 */
public class AnnotationBean {
	
	@Resource(name="annotationPerson")
	private Person person;
//	@Autowired(required=false)
	private Bean bean;
	
	private String requiredAnnotation;
	private String autowiredAnnotation;
	private String injectAnnotation;
	
	public AnnotationBean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}
	
	@Autowired
	public AnnotationBean(Bean bean) {
		this.bean = bean;
		System.out.println(this.getClass().getName() + "有参构造方法实例化");
		System.out.println("    @Autowired在构造方法上，注入" + this.bean);
	}

	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

	public String getRequiredAnnotation() {
		return requiredAnnotation;
	}
	@Required // 这个注解只是表明受影响的bean的属性必须在bean的定义中或者是自动装配中通过明确的属性值在配置时来填充
	public void setRequiredAnnotation(String requiredAnnotation) {
		this.requiredAnnotation = requiredAnnotation;
		System.out.println("	" + this.requiredAnnotation);
	}

	public String getAutowiredAnnotation() {
		return autowiredAnnotation;
	}
	@Autowired
	public void setAutowiredAnnotation(String autowiredAnnotation) {
		this.autowiredAnnotation = autowiredAnnotation;
		System.out.println("	" + this.autowiredAnnotation);
	}

	public String getInjectAnnotation() {
		return injectAnnotation;
	}
	@Inject // JSR330
	public void setInjectAnnotation(String injectAnnotation) {
		this.injectAnnotation = injectAnnotation;
		System.out.println("	" + this.injectAnnotation);
	}
}
