package com.ljt.study.code.proxy.advisor.spring;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * DefaultAdvisorAutoProxyCreator
 *
 * @author LiJingTang
 * @date 2021-06-26 11:39
 */
@Configuration
@Import(DefaultAdvisorAutoProxyCreator.class)
@ComponentScan("com.ljt.study.code.proxy.advisor.bean")
public class Config {


}
