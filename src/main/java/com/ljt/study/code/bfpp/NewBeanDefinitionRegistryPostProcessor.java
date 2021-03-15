package com.ljt.study.code.bfpp;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author LiJingTang
 * @date 2021-03-15 10:12
 */
@Slf4j
@Data
public class NewBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    private String name;

    public NewBeanDefinitionRegistryPostProcessor() {
        log.info("{} 执行构造方法 name={}", this.getClass().getSimpleName(), name);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        log.info("{} 调用postProcessBeanDefinitionRegistry name={}", this.getClass().getSimpleName(), name);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("{} 调用postProcessBeanFactory", this.getClass().getSimpleName());
    }

}
