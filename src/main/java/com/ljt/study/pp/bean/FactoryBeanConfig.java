package com.ljt.study.pp.bean;

import com.ljt.study.entity.User;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FactoryBean
 *
 * @author LiJingTang
 * @date 2020-01-03 21:14
 */
@Configuration
public class FactoryBeanConfig {

    @Bean
    public UserFactoryBean userFactoryBean() {
        return new UserFactoryBean();
    }

}

class UserFactoryBean implements FactoryBean<User> {

    @Override
    public User getObject() {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

}

