package com.ljt.study.transaction;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.ljt.study.entity.User;
import com.ljt.study.transaction.service.EmailService;
import com.ljt.study.transaction.service.UserService;
import com.ljt.study.util.TestUtils;

/**
 * -javaagent:D:/resource/jar/spring/spring-framework-4.2.0.RELEASE/libs/spring-instrument-4.2.0.RELEASE.jar
 * 
 * @author LiJingTang
 * @version 2015年9月17日下午5:29:01
 */
public class TransactionTest {

	private static final String MODULE = "/transaction/";
	private static final String CONFIG_XML = "xml";
	private static final String CONFIG_ANNOTATION = "annotation";
	private static final String CONFIG_ASPECTJ = "aspectj";
	private ApplicationContext applicationContext;
	
	@Test
	public void testXML() {
		this.applicationContext = TestUtils.instanceContext(MODULE, CONFIG_XML);
		UserService userService = this.applicationContext.getBean("userService", UserService.class);
		
		User user = TestUtils.newUserInstance();
		userService.insertUser(user);
		System.out.println(user);
	}
	
	@Test
	public void testAnnotation() {
		this.applicationContext = TestUtils.instanceContext(MODULE, CONFIG_ANNOTATION);
		EmailService emailService = this.applicationContext.getBean("emailService", EmailService.class);
		
		emailService.insertEmail(null);
	}
	
	@Test
	public void testTransaction() {
		this.applicationContext = TestUtils.instanceContext(MODULE, CONFIG_ANNOTATION);
		UserService userService = this.applicationContext.getBean("userService", UserService.class);
		
		User user = TestUtils.newUserInstance();
		userService.insertUser(user);
		System.out.println(user);
	}
	
	@Test
	public void testAspectJ() {
		this.applicationContext = TestUtils.instanceContext(MODULE, CONFIG_ASPECTJ);
		UserService userService = this.applicationContext.getBean("userService", UserService.class);
		
		User user = TestUtils.newUserInstance();
		userService.insertUser(user);
		System.out.println(user);
	}
	
}
