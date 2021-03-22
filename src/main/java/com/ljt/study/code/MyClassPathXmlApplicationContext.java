package com.ljt.study.code;

import com.ljt.study.code.bfpp.CustomBeanDefinitionRegistryPostProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
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

    public MyClassPathXmlApplicationContext(String... configLocation) throws BeansException {
        super(configLocation);
    }

    @Override
    protected void initPropertySources() {
        log.info("扩展 initPropertySources");
        getEnvironment().setRequiredProperties("OS");
    }

    @Override
    protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
        log.info("扩展 customizeBeanFactory");
        /*
         * 两个xml中含有相同名称的bean
         */
        super.setAllowBeanDefinitionOverriding(Boolean.TRUE);
        super.setAllowCircularReferences(Boolean.TRUE);
        super.customizeBeanFactory(beanFactory);
        /*
         * PostProcessorRegistrationDelegate.invokeBeanFactoryPostProcessors(beanFactory, getBeanFactoryPostProcessors())
         * 此种方式启动流程中getBeanFactoryPostProcessors()有值
         */
        super.addBeanFactoryPostProcessor(new CustomBeanDefinitionRegistryPostProcessor());
        log.info("添加 CustomBeanDefinitionRegistryPostProcessor");
    }

    @Override
    protected void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        log.info("扩展 postProcessBeanFactory");
    }

}
