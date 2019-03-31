package com.ljt.study.ioc.bean.xml.methodinjection;

/**
 * @author LiJingTang
 * @version 2015年8月23日 下午9:55:13
 */
class Command {
	
	private Object state;
	
	public Object execute() {
		return null;
	}

	public Object getState() {
		return state;
	}
	public void setState(Object state) {
		this.state = state;
	}
}