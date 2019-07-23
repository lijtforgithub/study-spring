package com.ljt.study.senior.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LiJingTang
 * @version 2019年3月30日 下午9:44:17
 */
@Slf4j
public class CustomBeanPostProcessor implements BeanPostProcessor {
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		log.debug("bean => {}", bean);
		log.debug("beanName => {}", beanName);
		System.out.println("... postProcessBeforeInitialization ...");
		
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("... postProcessAfterInitialization ...");
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
	
}
