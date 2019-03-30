package com.ljt.study.annotation.lifecycle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author LiJingTang
 * @version 2019年3月30日 下午9:23:24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LifeCycleConfig.class)
public class LiftCycleTest {
	
	@Test
	public void testLiftCycle() {
		
	}

}
