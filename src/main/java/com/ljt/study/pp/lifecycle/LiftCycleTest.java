package com.ljt.study.pp.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author LiJingTang
 * @date 2020-01-04 09:59
 */
@SpringJUnitConfig(LifeCycleConfig.class)
public class LiftCycleTest {

    @Autowired
    private Car car;

    @Test
    public void testLiftCycle() {

    }

}
