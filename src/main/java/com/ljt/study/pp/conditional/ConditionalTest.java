package com.ljt.study.pp.conditional;

import com.ljt.study.AbstractTest;
import org.junit.jupiter.api.Test;

/**
 * @author LiJingTang
 * @date 2020-01-04 10:11
 */
public class ConditionalTest extends AbstractTest {

    @Test
    public void testCondition() {
        setApplicationContext(ConditionConfig.class);
        printBeanDefinition();
    }

}
