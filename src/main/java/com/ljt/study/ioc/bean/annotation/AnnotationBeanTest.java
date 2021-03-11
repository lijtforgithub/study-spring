package com.ljt.study.ioc.bean.annotation;

import com.ljt.study.AbstractTest;
import org.junit.jupiter.api.Test;

/**
 * @author LiJingTang
 * @date 2020-01-04 11:05
 */
public class AnnotationBeanTest extends AbstractTest {

    @Test
    public void testComponentScan() {
        setApplicationContext("component-scan");

        printBeanDefinition();
    }

}
