package com.ljt.study.pp.scope;

import com.ljt.study.entity.User;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 单例
 *
 * @author LiJingTang
 * @date 2020-01-03 22:02
 */
@Configuration
public class SingletonConfig {

    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    @Bean
    public User user() {
        System.out.println("向IoC容器里添加对象");
        return new User();
    }

}
