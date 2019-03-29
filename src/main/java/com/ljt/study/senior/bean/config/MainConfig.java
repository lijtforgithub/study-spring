package com.ljt.study.senior.bean.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ljt.study.entity.User;
import com.ljt.study.senior.bean.entity.Animal;

/**
 * @author LiJingTang
 * @version 2019年3月27日 下午10:38:38
 */
@Configuration
@ComponentScan("com.ljt.study.senior.bean.entity")
@Import(Animal.class)
public class MainConfig {

	/**
	 * 默认使用方法名作为Bean的ID
	 */
	@Bean
	public User user() {
		return new User();
	}
	
	/**
	 * 自定义Bean的ID
	 */
	@Bean("user_x")
	public User userX() {
		return new User();
	}
	
}
