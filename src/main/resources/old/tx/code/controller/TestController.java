package com.ljt.study.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljt.study.entity.User;
import com.ljt.study.transaction.service.JTAService;

/**
 * @author LiJingTang
 * @version 2015年11月5日 下午7:59:27
 */
@Controller("transactionTestController")
@RequestMapping("/lijt/spring/transaction")
public class TestController {
	
	@Autowired
	private JTAService jtaService;
	
	@RequestMapping("/jta.htm")
	@ResponseBody
	public String testJTA() {
		User user = new User();
		user.setName("name_JTA");
		user.setPassword("password_JTA");
		
		this.jtaService.saveUser(user);
		
		return "SUCESS";
	}

}
