package com.ljt.study.annotation.conditional;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ljt.study.AbstractTest;

/**
 * @author LiJingTang
 * @version 2019年3月29日 下午11:20:38
 */
public class ConditionalTest extends AbstractTest {
	
	@Test
	public void testCondition() {
		applicationContext = new AnnotationConfigApplicationContext(ConditionConfig.class);
		printBeanDefinition();
	}

}
