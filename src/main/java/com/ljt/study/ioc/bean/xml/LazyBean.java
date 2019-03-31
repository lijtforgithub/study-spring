package com.ljt.study.ioc.bean.xml;

/**
 * @author LiJingTang
 * @version 2015年8月23日 下午7:25:55
 */
public class LazyBean {

	private String explain;
	
	public LazyBean(){}
	
	public LazyBean(String explain) {
		this.explain = explain;
		System.out.println(this.getClass().getName() + this.explain);
	}
	
}
