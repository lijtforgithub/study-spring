package com.ljt.study.senior.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author LiJingTang
 * @version 2019年3月30日 下午9:44:17
 */
public class CustomBeanPostProcessor implements BeanPostProcessor {
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomBeanPostProcessor.class);

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		LOG.debug("bean => {}", bean);
		LOG.debug("beanName => {}", beanName);
		System.out.println("... postProcessBeforeInitialization ...");
		
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("... postProcessAfterInitialization ...");
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}
	
}
