package com.ljt.study.annotation.conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.ljt.study.entity.User;

/**
 * @author LiJingTang
 * @version 2019年3月29日 下午11:08:36
 */
@Configuration
public class MainConfig {

	@Bean
	@Conditional(ConditionOnWindows.class)
	public User billGates() {
		User user = new User();
		user.setName("比尔.盖茨");
		
		return user;
	}
	
	@Bean
	@Conditional(ConditionOnLinux.class)
	public User LinusTorvalds () {
		User user = new User();
		user.setName("林纳斯·托瓦兹");
		
		return user;
	}
	
}
