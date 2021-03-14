package com.ljt.study.pp.scope;

import com.ljt.study.AbstractTest;
import com.ljt.study.entity.User;
import org.junit.jupiter.api.Test;

/**
 * @author LiJingTang
 * @date 2020-01-03 22:01
 */
public class ScopeTest extends AbstractTest {

    /**
     * 单例
     */
    @Test
    public void testSingleton() {
        setApplicationContext(SingletonConfig.class);
        printBeanDefinition();

        User user1 = applicationContext.getBean(User.class);
        User user2 = applicationContext.getBean(User.class);
        System.out.println(user1 == user2);
    }

    /**
     * 原型
     */
    @Test
    public void testPrototype() {
        setApplicationContext(PrototypeConfig.class);
        printBeanDefinition();

        User user1 = applicationContext.getBean(User.class);
        User user2 = applicationContext.getBean(User.class);
        System.out.println(user1 == user2);
    }

    /**
     * 懒加载
     */
    @Test
    public void testLazy() {
        setApplicationContext(LazyConfig.class);
        printBeanDefinition();

        User user1 = applicationContext.getBean(User.class);
        User user2 = applicationContext.getBean(User.class);
        System.out.println(user1 == user2);
    }

}
