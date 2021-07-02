package com.ljt.study.ioc.di.annotation.qualifier;

/**
 * @author LiJingTang
 * @version 2015年8月27日 下午7:09:01
 */
public class PrimaryBean {

	private String data;
	
	public PrimaryBean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}
	
	public PrimaryBean(String data) {
		this.data = data;
		System.out.println(this.getClass().getName() + "有参构造方法实例化" + this.data);
	}

	public String getData() {
		return data;
	}
	
}
