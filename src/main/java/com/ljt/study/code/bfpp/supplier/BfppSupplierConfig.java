package com.ljt.study.code.bfpp.supplier;

import com.ljt.study.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiJingTang
 * @date 2021-03-19 11:55
 */
@Configuration
public class BfppSupplierConfig {

    @Bean
    public User user() {
        return new User();
    }

    @Bean
    public UserSupplierBeanDefinitionRegistryPostProcessor userBfpp() {
        return new UserSupplierBeanDefinitionRegistryPostProcessor();
    }

}
