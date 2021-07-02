package com.ljt.study.jms.service;

/**
 * @author LiJingTang
 * @version 2015年11月6日下午3:07:51
 */
public interface DBService {
	
	String SQL_INSERT = "INSERT INTO T_TEST(data) VALUES(?)";
	
	void saveData(String data);

}
