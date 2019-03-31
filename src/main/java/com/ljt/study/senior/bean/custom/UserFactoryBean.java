package com.ljt.study.senior.bean.custom;

import org.springframework.beans.factory.FactoryBean;

import com.ljt.study.entity.User;

/**
 * @author LiJingTang
 * @version 2019年3月30日 下午7:53:35
 */
public class UserFactoryBean implements FactoryBean<User> {

	@Override
	public User getObject() throws Exception {
		return new User();
	}

	@Override
	public Class<?> getObjectType() {
		return User.class;
	}

}
