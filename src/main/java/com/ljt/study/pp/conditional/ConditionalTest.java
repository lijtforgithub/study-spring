package com.ljt.study.pp.conditional;

import com.ljt.study.AbstractTest;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author LiJingTang
 * @date 2020-01-04 10:11
 */
public class ConditionalTest extends AbstractTest {

    @Test
    public void testCondition() {
        applicationContext = new AnnotationConfigApplicationContext(ConditionConfig.class);
        printBeanDefinition();
    }

}
