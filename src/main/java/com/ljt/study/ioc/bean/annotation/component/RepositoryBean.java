package com.ljt.study.ioc.bean.annotation.component;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * @author LiJingTang
 * @version 2015年8月28日 下午10:44:31
 */

@Scope("prototype") // 容器初始化的就不会实例化了
@Repository
public class RepositoryBean {
	
	public RepositoryBean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}
	
}
