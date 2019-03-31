package com.ljt.study.senior.bean.entity;

import org.springframework.stereotype.Component;

/**
 * @author LiJingTang
 * @version 2019年3月28日 下午9:30:39
 */
@Component
public class Cat {

	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
