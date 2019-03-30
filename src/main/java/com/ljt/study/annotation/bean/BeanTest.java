package com.ljt.study.annotation.bean;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ljt.study.annotation.AbstractTest;
import com.ljt.study.annotation.bean.config.ComponentScanConfig;
import com.ljt.study.annotation.bean.config.FactoryBeanConfig;
import com.ljt.study.annotation.bean.config.ImportConfig;
import com.ljt.study.annotation.bean.config.BeanConfig;

/**
 * @author LiJingTang
 * @version 2019年3月27日 下午10:46:52
 */
public class BeanTest extends AbstractTest {

	@Test
	public void testBean() {
		applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
		printBeanDefinition();
	}
	
	@Test
	public void testComponentScan() {
		applicationContext = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
		printBeanDefinition();
	}
	
	@Test
	public void testImport() {
		applicationContext = new AnnotationConfigApplicationContext(ImportConfig.class);
		printBeanDefinition();
	}
	
	@Test
	public void testFactoryBean() {
		applicationContext = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
		printBeanDefinition();
		
		System.out.println("userFactoryBean => " + applicationContext.getBean("userFactoryBean").getClass());
		System.out.println("&userFactoryBean => " + applicationContext.getBean("&userFactoryBean").getClass());
	}
	
}
