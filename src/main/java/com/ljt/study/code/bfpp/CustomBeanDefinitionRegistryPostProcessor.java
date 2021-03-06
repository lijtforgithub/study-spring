package com.ljt.study.code.bfpp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author LiJingTang
 * @date 2021-03-14 16:34
 */
@Slf4j
public class CustomBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        log.info("{} 调用postProcessBeanDefinitionRegistry", this.getClass().getSimpleName());

        /*
         *  invokeBeanFactoryPostProcessors 重复调用
         *  postProcessorNames = beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false)
         *  的原因
         */
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(NewBeanDefinitionRegistryPostProcessor.class);
        /*
         * currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class))
         */
        builder.addPropertyValue("name", "实例化后再调用");
        registry.registerBeanDefinition("New-PostProcessor", builder.getBeanDefinition());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("{} 调用postProcessBeanFactory", this.getClass().getSimpleName());
    }

}
