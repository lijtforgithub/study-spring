package com.ljt.study.code.proxy.advisor;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiJingTang
 * @date 2021-06-26 11:39
 */
@Configuration
class Config {

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public LogAdvisor logAdvisor() {
        return new LogAdvisor();
    }

    @Bean
    public CustomService customService() {
        return new CustomServiceImpl();
    }

}
