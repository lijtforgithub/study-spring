package com.ljt.study.code.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author LiJingTang
 * @date 2021-03-14 13:18
 */
public class CustomAwareProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof PrintAware) {
            PrintAware printAware = (PrintAware) bean;
            printAware.printClass();
        }
        return bean;
    }

}
