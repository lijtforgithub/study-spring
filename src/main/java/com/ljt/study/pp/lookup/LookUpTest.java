package com.ljt.study.pp.lookup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author LiJingTang
 * @date 2020-01-04 09:33
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LookUpConfig.class)
public class LookUpTest {

    @Autowired
    private SingletonContextGetBean getBean;
    @Autowired
    private SingletonLookupBean lookupBean;
    @Autowired
    private PrototypeLookupBean prototypeBean;

    @Test
    public void testSingleton() {
        for (int i = 0; i < 2; i++) {
            getBean.print();
        }
        for (int i = 0; i < 2; i++) {
            lookupBean.print();
        }
    }

    @Test
    public void testPrototype() {
        for (int i = 0; i < 2; i++) {
            prototypeBean.print();
        }
    }

}
