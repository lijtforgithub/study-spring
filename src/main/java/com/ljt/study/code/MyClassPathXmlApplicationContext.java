package com.ljt.study.code;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 扩展 ApplicationContext
 *
 * @author LiJingTang
 * @date 2021-03-12 09:45
 */
@Slf4j
public class MyClassPathXmlApplicationContext extends ClassPathXmlApplicationContext {

    public MyClassPathXmlApplicationContext(String configLocation) throws BeansException {
        super(configLocation);
    }

    @Override
    protected void initPropertySources() {
        log.info("自定义 initPropertySources");
        getEnvironment().setRequiredProperties("OS");
    }

    @Override
    protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
        log.info("自定义 customizeBeanFactory");
        super.setAllowBeanDefinitionOverriding(Boolean.TRUE);
        super.setAllowCircularReferences(Boolean.TRUE);
        super.customizeBeanFactory(beanFactory);
    }

}
