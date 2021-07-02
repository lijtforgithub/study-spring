package com.ljt.study.transaction.service;

import com.ljt.study.entity.User;

/**
 * @author LiJingTang
 * @version 2015年9月23日上午10:38:35
 */
public interface UserService {
	
	String SQL_INSERT = "INSERT INTO T_USER(name, password) VALUES(?, ?)";

	User getUser(int id);
	
	void insertUser(User user);
	
	void updateUser(User user);
	
}
