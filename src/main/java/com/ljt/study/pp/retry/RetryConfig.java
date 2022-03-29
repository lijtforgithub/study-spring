package com.ljt.study.pp.retry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author jtli3
 * @date 2022-03-29 18:53
 */
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableRetry
@Configuration(proxyBeanMethods = false)
class RetryConfig {

    @Bean
    RetryService retryService() {
        return new RetryService();
    }

    @Bean
    CustomRetryListener customRetryListener() {
        return new CustomRetryListener();
    }

    @Bean
    CustomMethodInterceptor customMethodInterceptor() {
        return new CustomMethodInterceptor();
    }

}
