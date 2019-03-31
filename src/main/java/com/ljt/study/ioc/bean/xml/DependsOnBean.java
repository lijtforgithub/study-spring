package com.ljt.study.ioc.bean.xml;

/**
 * @author LiJingTang
 * @version 2015年8月23日 下午6:36:43
 */
public class DependsOnBean {

	private Bean bean;
	private String explain;
	
	public DependsOnBean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}

	public Bean getBean() {
		return bean;
	}
	public void setBean(Bean bean) {
		this.bean = bean;
	}
	public void setExplain(String explain) {
		this.explain = explain;
		System.out.println("	基于Setter注入依赖：" + this.explain);
	}
	
}

class FirstBean {
	
	public FirstBean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}
	
}

class SecondBean {
	
	public SecondBean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}
	
}

class ThirdBean {
	
	public ThirdBean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}
	
}
