package com.ljt.study.annotation.bean;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ljt.study.annotation.AbstractTest;
import com.ljt.study.annotation.bean.config.ComponentScanConfig;
import com.ljt.study.annotation.bean.config.ImportConfig;
import com.ljt.study.annotation.bean.config.MainConfig;

/**
 * @author LiJingTang
 * @version 2019年3月27日 下午10:46:52
 */
public class BeanTest extends AbstractTest {

	@Test
	public void testMain() {
		applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
	}
	
	@Test
	public void testComponentScan() {
		applicationContext = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
	}
	
	@Test
	public void testImport() {
		applicationContext = new AnnotationConfigApplicationContext(ImportConfig.class);
	}
	
}
