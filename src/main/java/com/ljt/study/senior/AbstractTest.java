package com.ljt.study.senior;

import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.context.ApplicationContext;

/**
 * @author LiJingTang
 * @version 2019年3月28日 下午8:59:37
 */
public abstract class AbstractTest {
	
	protected ApplicationContext applicationContext;
	
	public void printContextBean() {
		Objects.requireNonNull(applicationContext, "IOC容器为空");
		
		Stream.of(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
	}

}
