package com.ljt.study.pp.scope;

import com.ljt.study.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * 懒加载
 *
 * @author LiJingTang
 * @date 2020-01-03 21:59
 */
@Configuration
public class LazyConfig {

    @Lazy
    @Bean
    public User user() {
        System.out.println("向IoC容器里添加对象");
        return new User();
    }

}
