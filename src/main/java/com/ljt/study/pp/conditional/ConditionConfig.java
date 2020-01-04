package com.ljt.study.pp.conditional;

import com.ljt.study.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiJingTang
 * @date 2020-01-04 10:10
 */
@Configuration
public class ConditionConfig {

    @Bean
    @Conditional(ConditionOnWindows.class)
    public User billGates() {
        User user = new User();
        user.setName("比尔.盖茨");

        return user;
    }

    @Bean
    @Conditional(ConditionOnLinux.class)
    public User LinusTorvalds() {
        User user = new User();
        user.setName("林纳斯·托瓦兹");

        return user;
    }

}
