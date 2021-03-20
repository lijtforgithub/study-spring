package com.ljt.study.code.bpp.instantiation;

import com.ljt.study.code.bpp.Bpp;
import com.ljt.study.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiJingTang
 * @date 2021-03-19 10:24
 */
@Configuration
public class InstantiationAwareBppConfig {

    @Bean
    public Bpp bpp() {
        return new Bpp();
    }

    @Bean
    public User user() {
        return new User();
    }

    @Bean
    public CustomInstantiationAwareBeanPostProcessor instantiationAwareBpp() {
        return new CustomInstantiationAwareBeanPostProcessor();
    }

}
