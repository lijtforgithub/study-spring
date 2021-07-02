package com.ljt.study.ioc.bean.annotation.component;

/**
 * @author LiJingTang
 * @version 2015年8月29日 下午8:54:13
 */

@CustomComponent
public class CustomComponentBean {

	public CustomComponentBean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}

}
