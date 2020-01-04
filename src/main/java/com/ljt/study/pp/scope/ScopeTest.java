package com.ljt.study.pp.scope;

import com.ljt.study.AbstractTest;
import com.ljt.study.entity.User;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
        applicationContext = new AnnotationConfigApplicationContext(SingletonConfig.class);
        System.out.println("IoC容器初始化完成");
        printBeanDefinition();

        System.out.println("containsBean => " + applicationContext.containsBean("user"));
        System.out.println("containsBeanDefinition => " + applicationContext.containsBeanDefinition("user"));

        User user1 = applicationContext.getBean(User.class);
        User user2 = applicationContext.getBean(User.class);
        System.out.println(user1 == user2);
    }

    /**
     * 原型
     */
    @Test
    public void testPrototype() {
        applicationContext = new AnnotationConfigApplicationContext(PrototypeConfig.class);
        System.out.println("IoC容器初始化完成");
        printBeanDefinition();

        System.out.println("containsBean => " + applicationContext.containsBean("user"));
        System.out.println("containsBeanDefinition => " + applicationContext.containsBeanDefinition("user"));

        User user1 = applicationContext.getBean(User.class);
        User user2 = applicationContext.getBean(User.class);
        System.out.println(user1 == user2);
    }

    /**
     * 懒加载
     */
    @Test
    public void testLazy() {
        applicationContext = new AnnotationConfigApplicationContext(LazyConfig.class);
        System.out.println("IoC容器初始化完成");
        printBeanDefinition();

        User user1 = applicationContext.getBean(User.class);
        User user2 = applicationContext.getBean(User.class);
        System.out.println(user1 == user2);
    }

}
