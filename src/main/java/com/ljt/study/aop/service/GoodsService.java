package com.ljt.study.aop.service;

/**
 * @author LiJingTang
 * @version 2015年9月14日下午2:11:06
 */

public class GoodsService {

	public void saveGoods() {
		System.out.println("	" + this.getClass().getName() + "...saveGoods()...");
	}
	
	public void orderMethod() {
		System.out.println("	" + this.getClass().getName() + "...orderMethod()...");
	}
	
}
