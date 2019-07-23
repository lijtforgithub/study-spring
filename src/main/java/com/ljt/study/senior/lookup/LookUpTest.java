package com.ljt.study.senior.lookup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author LiJingTang
 * @date 2019年7月23日 上午10:51:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LookUpConfig.class)
public class LookUpTest {
    
    @Autowired
    private SingletonBean bean;
    
    @Test
    public void test() {
        for (int i = 0; i < 3; i++) {
            bean.print();
        }
    }

}
