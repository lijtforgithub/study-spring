package com.ljt.study.pp.bean;

import com.ljt.study.AbstractTest;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author LiJingTang
 * @date 2020-01-03 21:04
 */
public class BeanTest extends AbstractTest {

    /**
     * @Bean
     */
    @Test
    public void testBean() {
        applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        printBeanDefinition();
    }

    /**
     * @ComponentScan
     */
    @Test
    public void testComponentScan() {
        applicationContext = new AnnotationConfigApplicationContext(ComponentScanConfig.class);
        printBeanDefinition();
    }

    /**
     * @Import
     */
    @Test
    public void testImport() {
        applicationContext = new AnnotationConfigApplicationContext(ImportConfig.class);
        printBeanDefinition();
    }

    /**
     * FactoryBean
     */
    @Test
    public void testFactoryBean() {
        applicationContext = new AnnotationConfigApplicationContext(FactoryBeanConfig.class);
        printBeanDefinition();

        System.out.println("userFactoryBean => " + applicationContext.getBean("userFactoryBean").getClass());
        System.out.println("&userFactoryBean => " + applicationContext.getBean("&userFactoryBean").getClass());
    }

}
