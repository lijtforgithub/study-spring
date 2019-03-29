package com.ljt.study.senior.bean;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ljt.study.senior.AbstractTest;
import com.ljt.study.senior.bean.config.BeanConfig;
import com.ljt.study.senior.bean.config.ComponentScanConfig;

/**
 * @author LiJingTang
 * @version 2019年3月27日 下午10:46:52
 */
public class BeanTest extends AbstractTest {

	@Test
	public void testMain() {
		applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
	}
	
	@Test
	public void testComponentScan() {
		applicationContext = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
	}
	
}
