package com.ljt.study.ioc.bean.xml;

/**
 * @author LiJingTang
 * @date 2020-01-04 10:44
 */
public class BeanFactory {

    private static BeanFactory instance = new BeanFactory();

    private BeanFactory() {
    }

    public static BeanFactory createInstance() {
        System.out.println(BeanFactory.class.getName() + "使用静态工厂方法来实例化" + instance.getClass().getName());
        return instance;
    }

}
