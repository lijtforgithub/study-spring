package com.ljt.study.ioc.bean.xml;

/**
 * @author LiJingTang
 * @version 2015年8月22日 下午6:11:02
 */
public class BeanLocator {

    private static LocatorBean instance = new LocatorBean();

    public LocatorBean createLocatorBeanInstance() {
    	System.out.println(this.getClass().getName() + "使用实例工厂方法来实例化" + instance.getClass().getName());
        return instance;
    }
    
}

class LocatorBean {
	
}

