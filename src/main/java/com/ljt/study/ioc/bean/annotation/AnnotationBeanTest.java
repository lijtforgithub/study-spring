package com.ljt.study.ioc.bean.annotation;

import org.junit.Test;

import com.ljt.study.AbstractTest;

/**
 * @author LiJingTang
 * @version 2019年3月31日 下午10:19:08
 */
public class AnnotationBeanTest extends AbstractTest {
	
	@Test
	public void testComponentScan() {
		setApplicationContext("component-scan");
		
		printBeanDefinition();
	}

}
