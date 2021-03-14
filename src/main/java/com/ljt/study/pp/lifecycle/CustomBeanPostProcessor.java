package com.ljt.study.pp.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author LiJingTang
 * @date 2020-01-04 09:58
 */
@Slf4j
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Car) {
            System.out.println("postProcessBeforeInitialization");
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Car) {
            System.out.println("postProcessAfterInitialization");
        }

        return bean;
    }

}
