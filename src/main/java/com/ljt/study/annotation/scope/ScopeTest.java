package com.ljt.study.annotation.scope;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ljt.study.annotation.AbstractTest;
import com.ljt.study.entity.User;

/**
 * @author LiJingTang
 * @version 2019年3月30日 下午6:16:46
 */
public class ScopeTest extends AbstractTest {
	
	@Test
	public void testSingleton() {
		applicationContext = new AnnotationConfigApplicationContext(SingletonConfig.class);
		System.out.println("容器初始化完成");
		printBeanId();
		
		System.out.println("containsBean => " + applicationContext.containsBean("user"));
		System.out.println("containsBeanDefinition => " + applicationContext.containsBeanDefinition("user"));
		
		User user1 = applicationContext.getBean(User.class);
		User user2 = applicationContext.getBean(User.class);
		System.out.println(user1 == user2);
	}
	
	@Test
	public void testPrototype() {
		applicationContext = new AnnotationConfigApplicationContext(PrototypeConfig.class);
		System.out.println("容器初始化完成");
		printBeanId();
		
		System.out.println("containsBean => " + applicationContext.containsBean("user"));
		System.out.println("containsBeanDefinition => " + applicationContext.containsBeanDefinition("user"));
		
		User user1 = applicationContext.getBean(User.class);
		User user2 = applicationContext.getBean(User.class);
		System.out.println(user1 == user2);
	}
	
	@Test
	public void testLazy() {
		applicationContext = new AnnotationConfigApplicationContext(LazyConfig.class);
		System.out.println("容器初始化完成");
		printBeanId();
		
		User user1 = applicationContext.getBean(User.class);
		User user2 = applicationContext.getBean(User.class);
		System.out.println(user1 == user2);
	}

}
