package com.ljt.study.annotation.scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.ljt.study.entity.User;

/**
 * @author LiJingTang
 * @version 2019年3月29日 下午11:07:32
 */
@Configuration
public class LazyConfig {
	
	@Lazy
	@Bean
	public User user() {
		System.out.println("向容器里添加对象");
		return new User();
	}
	
}
