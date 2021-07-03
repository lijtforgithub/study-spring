package com.ljt.study.aop.service;

import com.ljt.study.aop.meta.AuditCode;
import com.ljt.study.aop.meta.Auditable;

/**
 * @author LiJingTang
 * @version 2015年9月16日下午2:55:47
 */
public class ParameterService {

	public String deleteParam(Long id, String message) {
		System.out.println("	" + this.getClass().getName() + "...delete()...");
//		throw new RuntimeException("测试异常参数传递");
		return "SUCESS";
	}
	
	@Auditable(AuditCode.PASS)
	public void saveParam() {
		System.out.println("	" + this.getClass().getName() + "...method()...");
	}
	
}
