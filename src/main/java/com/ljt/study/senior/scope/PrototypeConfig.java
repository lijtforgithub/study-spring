package com.ljt.study.senior.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.ljt.study.entity.User;

/**
 * @author LiJingTang
 * @version 2019年3月29日 下午11:07:32
 */
@Configuration
public class PrototypeConfig {
	
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Bean
	public User user() {
		System.out.println("向IoC容器里添加对象");
		return new User();
	}

}
