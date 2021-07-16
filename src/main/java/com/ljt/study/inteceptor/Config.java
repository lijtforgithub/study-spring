package com.ljt.study.inteceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LiJingTang
 * @date 2021-07-16 18:11
 */
@Configuration
class Config implements WebMvcConfigurer {

    @Bean
    CustomInterceptor1 interceptor1() {
        return new CustomInterceptor1();
    }

    @Bean
    CustomInterceptor2 interceptor2() {
        return new CustomInterceptor2();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor1());
        registry.addInterceptor(interceptor2());
    }

}
