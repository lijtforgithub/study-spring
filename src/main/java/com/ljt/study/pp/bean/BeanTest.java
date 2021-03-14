package com.ljt.study.pp.bean;

import com.ljt.study.AbstractTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author LiJingTang
 * @date 2020-01-03 21:04
 */
@Slf4j
public class BeanTest extends AbstractTest {

    /**
     * @Bean
     */
    @Test
    public void testBean() {
        setApplicationContext(BeanConfig.class);
        printBeanDefinition();
    }

    /**
     * @ComponentScan
     */
    @Test
    public void testComponentScan() {
        setApplicationContext(ComponentScanConfig.class);
        printBeanDefinition();
    }

    /**
     * @Import
     */
    @Test
    public void testImport() {
        setApplicationContext(ImportConfig.class);
        printBeanDefinition();
    }

    /**
     * FactoryBean
     */
    @Test
    public void testFactoryBean() {
        setApplicationContext(FactoryBeanConfig.class);
        printBeanDefinition();

        log.info("&userFactoryBean => {}", applicationContext.getBean("&userFactoryBean").getClass());
        log.info("userFactoryBean => {}", applicationContext.getBean("userFactoryBean").getClass());
    }

}
