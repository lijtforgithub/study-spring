package com.ljt.study.ioc.di.annotation.qualifier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author LiJingTang
 * @version 2015年8月27日 下午7:03:19
 */
@Configuration
public class ContextConfigQualifier {

	@Bean(name="firstPrimaryBean")
	@Primary
	public PrimaryBean firstPrimaryBean() {
		return new PrimaryBean("@Primary注解的firstPrimaryBean");
	}
	
	@Bean(name="secondPrimaryBean")
	public PrimaryBean secondPrimaryBean() {
		return new PrimaryBean("@Qualifier会覆盖@Primary注解secondPrimaryBean");
	}
	
	@Bean(name="qualifierBean")
	public QualifierBean qualifierBean() {
		return new QualifierBean();
	}

	@Bean
	public CustomQualifierBean customQualifierBean() {
		return new CustomQualifierBean();
	}

}
