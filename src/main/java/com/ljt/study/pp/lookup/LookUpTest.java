package com.ljt.study.pp.lookup;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author LiJingTang
 * @date 2020-01-04 09:33
 */
@SpringJUnitConfig(LookUpConfig.class)
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
