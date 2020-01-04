package com.ljt.study.pp.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author LiJingTang
 * @date 2020-01-04 09:59
 */
@Configuration
@Import(CustomBeanPostProcessor.class)
public class LifeCycleConfig {

    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public Car car() {
        return new Car();
    }

}
