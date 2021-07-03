package com.ljt.study.aop.aspectj;

import com.ljt.study.aop.service.GoodsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

/**
 * @author LiJingTang
 * @date 2021-07-03 17:12
 */
@Configuration
@EnableSpringConfigured
public class ConfigurableConfig {

    @Bean
    public GoodsService goodsService() {
        return new GoodsService();
    }

}
