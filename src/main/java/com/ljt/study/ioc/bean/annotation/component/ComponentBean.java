package com.ljt.study.ioc.bean.annotation.component;

import org.springframework.stereotype.Component;

/**
 * @author LiJingTang
 * @version 2015年8月28日 下午10:42:49
 */

@Component // 一般情况下，Spring管理的组件，自动检测组件的默认和最多使用的范围是单例范
public class ComponentBean {

	private int i;
	private String explain;

	public ComponentBean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}

	public ComponentBean(String explain) {
		this.explain = explain;
	}

	public ComponentBean(String explain, int i) {
		this.i = i;
		this.explain = explain;
	}

	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}
	
}
