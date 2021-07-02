package com.ljt.study.jms.service;

import java.io.Serializable;

import javax.jms.Destination;

/**
 * @author LiJingTang
 * @version 2015年8月19日 下午6:52:16
 */
public interface ProducerService {

	void sendMessage(Destination destination, final String message);
	
	void sendResponseMessage(Destination destination, final String message);
	
	void sendConverterMessage(Destination destination, final Serializable obj);
	
}
