package com.ljt.study;

import java.util.Objects;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author LiJingTang
 * @version 2019年3月28日 下午8:59:37
 */
public abstract class AbstractTest {
	
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass()); 
	
	private static final String SPRING_BEAN = "org.springframework";
	private static final String PCK_PREFIX = "com.ljt.study";
	
	protected ApplicationContext applicationContext;
	
	protected void printBeanDefinition() {
		Objects.requireNonNull(applicationContext, "IoC容器为空");
		
		LOG.info("打印IoC容器里自定义的Bean开始");
		
		Stream.of(applicationContext.getBeanDefinitionNames())
			.filter(name -> !name.startsWith(SPRING_BEAN))
			.forEach(System.out::println);
		
		LOG.info("打印IoC容器里自定义的Bean结束");
	}
	
	private static final String SUFFIX = ".xml";
	
	protected void setApplicationContext(String fileName) {
		String pckSuffix = this.getClass().getPackage().getName().substring(PCK_PREFIX.length());
		String configLocations = pckSuffix.replaceAll("\\.", "/") + "/" + fileName + SUFFIX;
		applicationContext = new ClassPathXmlApplicationContext(configLocations);
		
		LOG.info("【{}】IoC容器初始化完成", configLocations);
	}
	
}
