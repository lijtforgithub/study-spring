package com.ljt.study.senior.bean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author LiJingTang
 * @version 2019年3月27日 下午10:46:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainConfig.class})
public class BeanTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void testBean() {
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		
		for (String name : beanDefinitionNames) {
			System.out.println(name);
		}
	}
	
}
