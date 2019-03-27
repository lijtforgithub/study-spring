package com.ljt.study.senior.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ljt.study.entity.User;

/**
 * @author LiJingTang
 * @version 2019年3月27日 下午10:38:38
 */
@Configuration
public class MainConfig {

	@Bean
	public User user() {
		return new User();
	}
	
}
