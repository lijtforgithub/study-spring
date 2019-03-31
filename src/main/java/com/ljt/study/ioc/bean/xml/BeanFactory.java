package com.ljt.study.ioc.bean.xml;

/**
 * @author LiJingTang
 * @version 2015年8月22日 下午6:14:14
 */
public class BeanFactory {
	
	private static BeanFactory instance = new BeanFactory();
	
	private BeanFactory() {}
	
	public static BeanFactory createInstance() {
		System.out.println(BeanFactory.class.getName() + "使用静态工厂方法来实例化" + instance.getClass().getName());
		return instance;
	}
	
}
