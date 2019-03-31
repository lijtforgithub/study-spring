package com.ljt.study.ioc.di.xml;

import org.junit.Test;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.ljt.study.AbstractTest;

/**
 * @author LiJingTang
 * @version 2019年3月31日 下午2:05:12
 */
public class DITest extends AbstractTest {
	
	@Test
	public void testConstructor() {
		setApplicationContext("constructor");
	}
	
	@Test
	public void testSetter() {
		setApplicationContext("setter");
		
		System.out.println("测试两种依赖注入方法一起使用属性的值：" + (applicationContext.getBean("diBeanId", SetterBean.class).getExplain()));
	}
	
	@Test
	public void testCollection() {
		setApplicationContext("collection");
		
		// 集合注入
		CollectionBean collBean = applicationContext.getBean("collBean", CollectionBean.class);
		System.out.println("List 默认注入的类型: " + collBean.getList().getClass().getName() + collBean.getList());
		System.out.println("Set 默认注入的类型: " + collBean.getSet().getClass().getName() + collBean.getSet());
		System.out.println("Map 默认注入的类型: " + collBean.getMap().getClass().getName() + collBean.getMap());
		System.out.println("Properties" + collBean.getProps());
		
		// 集合合并
		CollectionBean childCollBean = applicationContext.getBean("childCollBean", CollectionBean.class);
		System.out.println("子集合Properties" + childCollBean.getProps());
	}
	
	@Test
	public void testNameSpace() {
		setApplicationContext("namespace");
		
		PropertyPlaceholderConfigurer dbConfig = applicationContext.getBean("dbProperties", PropertyPlaceholderConfigurer.class);
		System.out.println(dbConfig.getClass().getName());
	}
	
	@Test
	public void testAutowire() {
		setApplicationContext("autowire");
		
		// byName
		AutowireBean autowireByNameBean = applicationContext.getBean("autowireByNameBean", AutowireBean.class);;
		System.out.println("没有找到相应bean的名字：" + autowireByNameBean.getDependsBean()+ " " + autowireByNameBean.getBean());
		
		// byType
		AutowireBean autowireByTypeBean = applicationContext.getBean("autowireByTypeBean", AutowireBean.class);;
		System.out.println("没有找到相应bean的类型：" + autowireByTypeBean.getDependsBean()+ " " + autowireByTypeBean.getBean());
		
		// constructor
		AutowireBean autowireConstructorBean = applicationContext.getBean("autowireConstructorBean", AutowireBean.class);;
		System.out.println("没有找到相应constructor：" + autowireConstructorBean.getDependsBean()+ " " + autowireConstructorBean.getBean());
	}
	
}
