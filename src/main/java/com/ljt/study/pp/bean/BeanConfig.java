package com.ljt.study.pp.bean;

import com.ljt.study.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Bean 方式
 *
 * @author LiJingTang
 * @date 2020-01-03 21:00
 */
@Configuration
//@ComponentScan("com.ljt.study.pp.bean.entity")
public class BeanConfig {

    /**
     * 默认使用方法名作为Bean的ID
     */
    @Bean
    public User user() {
        return new User();
    }

    /**
     * 自定义Bean的ID
     */
    @Bean("user_x")
    public User userX() {
        return new User();
    }

}
