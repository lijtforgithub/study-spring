package com.ljt.study.ioc.di.xml;

import com.ljt.study.ioc.bean.xml.Bean;
import com.ljt.study.ioc.bean.xml.DependsOnBean;

/**
 * @author LiJingTang
 * @version 2015年8月23日 下午8:30:36
 */
public class AutowireBean {

	private String explain;
	private Bean bean;
	private DependsOnBean dependsBean;
	
	public AutowireBean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}
	
	public AutowireBean(DependsOnBean dependsBean, String explain) {
		this.dependsBean = dependsBean;
		this.explain = explain;
		System.out.println(this.getClass().getName() + "有参构造方法实例化" + this.explain);
	}

	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
		System.out.println("	基于Setter注入依赖：" + this.explain);
	}
	public Bean getBean() {
		return bean;
	}
	public void setBean(Bean bean) {
		this.bean = bean;
		System.out.println("	基于Setter注入依赖：" + this.bean.getClass().getName());
	}
	public DependsOnBean getDependsBean() {
		return dependsBean;
	}
	public void setDependsBean(DependsOnBean dependsBean) {
		this.dependsBean = dependsBean;
		System.out.println("	基于Setter注入依赖：" + this.dependsBean.getClass().getName());
	}
	
}
