package com.ljt.study.pp.scope;

import com.ljt.study.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author LiJingTang
 * @date 2020-01-03 22:04
 */
@Slf4j
@Configuration
public class PrototypeConfig {

    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Bean
    public User user() {
        log.debug("向容器中添加对象");
        return new User();
    }

}
