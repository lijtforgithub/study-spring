package com.ljt.study.senior.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author LiJingTang
 * @version 2019年3月30日 下午9:23:56
 */
public class Car implements InitializingBean, DisposableBean {
	
	public Car() {
		super();
		System.out.println("...constructor...");
	}

	public void initMethod() {
		System.out.println("...initMethod...");
	}
	
	public void destroyMethod() {
		System.out.println("...destroyMethod...");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("...afterPropertiesSet...");
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("...destroy...");
	}
	
	@PostConstruct
	public void postConstruct() {
		System.out.println("...postConstruct...");
	}
	
	@PreDestroy
	public void preDestroy() {
		System.out.println("...preDestroy...");
	}
	
}
