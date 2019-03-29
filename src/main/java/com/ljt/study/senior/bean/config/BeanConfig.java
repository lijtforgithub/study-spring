package com.ljt.study.senior.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ljt.study.entity.User;

/**
 * @author LiJingTang
 * @version 2019年3月27日 下午10:38:38
 */
@Configuration
@ComponentScan("")
public class MainConfig {

	@Bean
	public User user() {
		return new User();
	}
	
	@Bean
	public User user1() {
		return new User();
	}
	
	@Bean("user2")
	public User userX() {
		return new User();
	}
	
}
