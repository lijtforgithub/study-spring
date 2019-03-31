package com.ljt.study.ioc.bean.annotation.component;

import org.springframework.stereotype.Service;

/**
 * @author LiJingTang
 * @version 2015年8月28日 下午10:13:53
 */

@Service
public class ServiceBean {
	
	public ServiceBean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}

}
