package com.ljt.study.entity;

import java.io.Serializable;

/**
 * @author LiJingTang
 * @version 2015年9月23日上午10:34:54
 */
public class User implements Serializable {

	private static final long serialVersionUID = -8368263216275207741L;
	
	private int id;
	private String name;
	private String password;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	
}
