package com.ljt.study.transaction.service;

import com.ljt.study.entity.Email;

/**
 * @author LiJingTang
 * @version 2015年9月23日下午1:10:11
 */
public interface EmailService {
	
	Email getEmail(int id);
	
	void insertEmail(Email email);
	
	void updateEmail(Email email);

}
