package com.ljt.study.annotation;

import java.util.Objects;
import java.util.stream.Stream;

import org.junit.After;
import org.springframework.context.ApplicationContext;

/**
 * @author LiJingTang
 * @version 2019年3月28日 下午8:59:37
 */
public abstract class AbstractTest {
	
	private static final String SPRING_BEAN = "org.springframework";
	
	protected ApplicationContext applicationContext;
	
	@After
	public void printContextBean() {
		Objects.requireNonNull(applicationContext, "容器为空");
		
		System.out.println("\r\n****** 打印容器里的Bean ******");
		
		Stream.of(applicationContext.getBeanDefinitionNames())
			.filter(name -> !name.startsWith(SPRING_BEAN))
			.forEach(System.out::println);
	}
	
}
