package com.ljt.study.ioc.di.annotation.qualifier;

import com.ljt.study.ioc.di.annotation.meta.GenreQualifier;
import com.ljt.study.ioc.di.annotation.meta.OfflineQualifier;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author LiJingTang
 * @version 2015年8月28日 下午6:41:16
 */
public class CustomQualifierBean {

	private String data;
	@Autowired
	@OfflineQualifier
	private PrimaryBean offlinePrimaryBean;
	@Autowired
	@GenreQualifier("First")
	private PrimaryBean firstPrimaryBean;
	private PrimaryBean secondPrimaryBean;
	
	public CustomQualifierBean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}
	
	public String getData() {
		return data;
	}
	
	public PrimaryBean getOfflinePrimaryBean() {
		return offlinePrimaryBean;
	}
	public PrimaryBean getSecondPrimaryBean() {
		return secondPrimaryBean;
	}
	@Autowired
	public void setSecondPrimaryBean(@GenreQualifier("Second") PrimaryBean secondPrimaryBean) {
		this.secondPrimaryBean = secondPrimaryBean;
		System.out.println("	@CustomQualifier(\"Second\")setter注入的PrimaryBean：" + this.secondPrimaryBean.getData());
	}
	public PrimaryBean getFirstPrimaryBean() {
		return firstPrimaryBean;
	}
}
