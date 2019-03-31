package com.ljt.study.ioc.bean.xml.extend;

/**
 * @author LiJingTang
 * @version 2015年8月25日 下午9:22:52
 */
public class Chinese {
	
	private String name;
	private String complexion;
	private int age;

	public Chinese() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComplexion() {
		return complexion;
	}
	public void setComplexion(String complexion) {
		this.complexion = complexion;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Chinese [name=" + name + ", complexion=" + complexion
				+ ", age=" + age + "]";
	}

	public void init() {
		System.out.println("	XML配置继承了person的age重写了name");
	}
	
}
