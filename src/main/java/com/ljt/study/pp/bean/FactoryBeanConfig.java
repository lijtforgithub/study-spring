package com.ljt.study.pp.bean;

import com.ljt.study.entity.User;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
class UserFactoryBean implements FactoryBean<User> {

    @Override
    public User getObject() {
        log.info("容器getBean()的时候才会创建User的实例，例如有其他bean需要注入该bean");
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

}

