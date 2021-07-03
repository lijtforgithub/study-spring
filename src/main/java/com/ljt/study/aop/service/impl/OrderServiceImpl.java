package com.ljt.study.aop.service.impl;

import com.ljt.study.aop.service.OrderService;

/**
 * @author LiJingTang
 * @version 2015年9月15日下午2:57:25
 */
public class OrderServiceImpl implements OrderService {

	@Override
	public void saveOrder() {
		System.out.println("	" + this.getClass().getName() + "...saveOrder()...");
	}
	
}
