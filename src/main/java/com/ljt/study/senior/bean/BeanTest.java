package com.ljt.study.senior.bean;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ljt.study.senior.AbstractTest;

/**
 * @author LiJingTang
 * @version 2019年3月27日 下午10:46:52
 */
public class BeanTest extends AbstractTest {

	@Test
	public void testBean() {
		applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
		printContextBean();
	}
	
}
