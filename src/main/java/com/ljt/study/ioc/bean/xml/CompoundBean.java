package com.ljt.study.ioc.bean.xml;

/**
 * @author LiJingTang
 * @version 2015年8月23日 下午6:22:20
 */
public class CompoundBean {

	private Bean bean = new Bean();
	
	public CompoundBean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}

	public Bean getBean() {
		return bean;
	}
	
}
