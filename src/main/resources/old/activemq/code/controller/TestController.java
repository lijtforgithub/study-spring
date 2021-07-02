package com.ljt.study.jms.controller;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljt.study.jms.service.ProducerService;

/**
 * @author LiJingTang
 * @version 2015年11月6日下午4:21:27
 */
@Controller("jmsTestController")
@RequestMapping("/lijt/spring/jms")
public class TestController {
	
	@Autowired
	private ProducerService producerService;
	@Resource(name="queue")
	private Destination queue;
	
	@RequestMapping("/jta.htm")
	@ResponseBody
	public void testJTA() {
    	producerService.sendMessage(queue, "JMS-Transaction-JTA"); 
	}

}
