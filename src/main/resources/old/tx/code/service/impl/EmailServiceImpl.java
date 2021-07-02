package com.ljt.study.transaction.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.ljt.study.entity.Email;
import com.ljt.study.transaction.service.EmailService;

/**
 * @author LiJingTang
 * @version 2015年9月23日下午1:11:37
 */
@Transactional
public class EmailServiceImpl implements EmailService {

	@Override
	public Email getEmail(int id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void insertEmail(Email email) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void updateEmail(Email email) {
		throw new UnsupportedOperationException();
	}

}
