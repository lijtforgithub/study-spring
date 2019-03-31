package com.ljt.study.ioc.bean.xml.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author LiJingTang
 * @version 2015年8月25日 下午11:07:44
 */
public class CustomBeanPostProcessor implements BeanPostProcessor {

	@Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println(this.getClass() + " => postProcessBeforeInitialization");
		
        return bean; 
    }

	@Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(this.getClass() + " => postProcessAfterInitialization");
        
        return bean;
    }
	
}