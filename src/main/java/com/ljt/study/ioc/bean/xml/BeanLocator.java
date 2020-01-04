package com.ljt.study.ioc.bean.xml;

/**
 * @author LiJingTang
 * @date 2020-01-04 10:45
 */
public class BeanLocator {

    private static final LocatorBean INSTANCE = new LocatorBean();

    public LocatorBean createLocatorBeanInstance() {
        System.out.println(this.getClass().getName() + "使用实例工厂方法来实例化" + INSTANCE.getClass().getName());
        return INSTANCE;
    }

}

class LocatorBean {

}
