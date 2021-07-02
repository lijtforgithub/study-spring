package com.ljt.study.ioc.bean.annotation;

import com.ljt.study.AbstractTest;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author LiJingTang
 * @date 2020-01-04 11:05
 */
class BeanTest extends AbstractTest {

    @Test
    void componentScan() {
        setApplicationContext("component-scan");

        printBeanDefinition();
    }

    @Test
    void config() {
        applicationContext = new AnnotationConfigApplicationContext();
        ((AnnotationConfigApplicationContext) applicationContext).register(ComponentConfig.class);
        ((AnnotationConfigApplicationContext) applicationContext).refresh();

        printBeanDefinition();
    }

}
