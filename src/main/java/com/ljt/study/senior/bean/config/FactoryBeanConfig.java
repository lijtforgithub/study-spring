package com.ljt.study.senior.bean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ljt.study.senior.bean.custom.UserFactoryBean;

/**
 * @author LiJingTang
 * @version 2019年3月30日 下午7:55:55
 */
@Configuration
public class FactoryBeanConfig {
	
	@Bean
	public UserFactoryBean userFactoryBean() {
		return new UserFactoryBean();
	}

}
