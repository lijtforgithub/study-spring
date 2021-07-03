package com.ljt.study.aop.introduction;

import com.ljt.study.aop.service.ParameterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 引入新的方法
 *
 * @author LiJingTang
 * @date 2021-07-03 15:04
 */
@Configuration
@EnableAspectJAutoProxy
public class IntroductionConfig {

    @Bean
    public IntroductionAspect introductionAspect() {
        return new IntroductionAspect();
    }

    @Bean
    public ParameterService parameterService() {
        return new ParameterService();
    }

}
