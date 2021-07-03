package com.ljt.study.aop.bean;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;

/**
 * 当定义在不同的切面里的两个通知都需要在一个相同的连接点中运行， 那么除非你指定，否则执行的顺序是未知的
 * 可以通过指定优先级来控制执行顺序。 在标准的Spring方法中可以在切面类中实现org.springframework.core.Ordered 接口或者用Order注解
 * 
 * @author LiJingTang
 * @version 2015年9月16日下午4:50:11
 */
@Aspect
@Order(10)
public class CustomOrderAspect {

	public CustomOrderAspect() {
		System.out.println(this.getClass() + "实例化");
	}

	@Pointcut("execution(* com.ljt.study.aop.service.GoodsService.orderMethod())")
	private void order(){}
	
	@Before("order()")
	public void before() {
		System.out.println("	" + this.getClass().getName() + "...before()...");
	}

	@After("order()")
	public void after() {
		System.out.println("	" + this.getClass().getName() + "...after()...");
	}
	
}
