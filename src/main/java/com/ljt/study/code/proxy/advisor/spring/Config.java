package com.ljt.study.code.proxy.advisor.spring;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiJingTang
 * @date 2021-06-26 11:39
 */
@Configuration
@ComponentScan("com.ljt.study.code.proxy.advisor.bean")
public class Config {

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
//        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

}
