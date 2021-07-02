package com.ljt.study.ioc.di.annotation;

import com.ljt.study.ioc.di.annotation.qualifier.PrimaryBean;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author LiJingTang
 * @version 2015年8月30日 下午2:08:07
 */
@Named("namedBean") // 相当于@Component注释标准
public class NamedBean {

	private PrimaryBean primaryBean;
	
	public NamedBean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}
	
	public PrimaryBean getPrimaryBean() {
		return primaryBean;
	}
	@Inject
	public void setPrimaryBean(@Named("main") PrimaryBean primaryBean) {
		this.primaryBean = primaryBean;
		System.out.println("");
	}

}
