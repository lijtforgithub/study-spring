package com.ljt.study.ioc.bean.xml.scope;

/**
 * @author LiJingTang
 * @version 2015年8月24日 下午7:33:14
 */
public class PrototypeBean {

	public PrototypeBean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}
	
	public void init() {
		System.out.println("	" + this.getClass().getName() + "调用init()方法");
	}
	
	public void destroy() {
		System.out.println("	" + this.getClass().getName() + "调用destroy()方法");
	}
	
}
