package com.ljt.study.ioc.bean.xml.extend;

/**
 * @author LiJingTang
 * @version 2015年8月25日 下午9:13:32
 */
public class Person {
	
	private String name;
	private int age;
	
	public Person() {
//		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
}
