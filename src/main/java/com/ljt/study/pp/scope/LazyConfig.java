package com.ljt.study.pp.scope;

import com.ljt.study.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * 懒加载
 *
 * @author LiJingTang
 * @date 2020-01-03 21:59
 */
@Slf4j
@Configuration
public class LazyConfig {

    @Lazy
    @Bean
    public User user() {
        log.debug("向容器中添加对象");
        return new User();
    }

}
