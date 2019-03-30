package com.ljt.study.annotation.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author LiJingTang
 * @version 2019年3月30日 下午9:22:02
 */
@Configuration
@Import(CustomBeanPostProcessor.class)
public class LifeCycleConfig {
	
	@Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
	public Car car() {
		return new Car();
	}

}
