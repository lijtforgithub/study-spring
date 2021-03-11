package com.ljt.study.ioc.bean.xml.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

/**
 * @author LiJingTang
 * @date 2020-01-04 10:58
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered {

    public CustomBeanFactoryPostProcessor() {
        System.out.println("被实例化了");
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println(this.getClass() + " => postProcessBeanFactory");
    }

}