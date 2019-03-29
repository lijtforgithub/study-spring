package com.ljt.study.senior.bean;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ljt.study.senior.AbstractTest;
import com.ljt.study.senior.bean.config.MainConfig;
import com.ljt.study.senior.bean.config.ComponentScanConfig;
import com.ljt.study.senior.bean.config.ImportRegistrarConfig;
import com.ljt.study.senior.bean.config.ImportSelectorConfig;

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
	public void testImportSelector() {
		applicationContext = new AnnotationConfigApplicationContext(ImportSelectorConfig.class);
	}
	
	@Test
	public void testImportRegistrar() {
		applicationContext = new AnnotationConfigApplicationContext(ImportRegistrarConfig.class);
	}
	
}
