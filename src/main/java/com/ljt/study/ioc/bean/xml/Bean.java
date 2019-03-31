package com.ljt.study.ioc.bean.xml;

/**
 * @author LiJingTang
 * @version 2015年8月22日 下午4:04:02
 */
public class Bean {
	
	private String emptyString;
	private String nullString;
	
	public Bean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}
	
	@SuppressWarnings("unused")
	private static class Nested {
		
		public Nested() {
			System.out.println(this.getClass().getName() + "实例化一个私有的静态内部类");
		}
	}
	
	@SuppressWarnings("unused")
	private class Inner {
		
		public Inner() {
			System.out.println(this.getClass().getName() + "实例化一个私有的成员内部类");
		}
	}

	public String getEmptyString() {
		return emptyString;
	}
	public void setEmptyString(String emptyString) {
		this.emptyString = emptyString;
	}
	public String getNullString() {
		return nullString;
	}
	public void setNullString(String nullString) {
		this.nullString = nullString;
	}
	
}
