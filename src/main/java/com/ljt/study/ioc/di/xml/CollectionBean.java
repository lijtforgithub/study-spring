package com.ljt.study.ioc.di.xml;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author LiJingTang
 * @version 2015年8月23日 下午1:07:01
 */
public class CollectionBean {

	private List<Object> list;
	private Set<Object> set;
	private Map<Object, Object> map;
	private Properties props;
	
	public CollectionBean() {
		System.out.println(this.getClass().getName() + "无参构造方法实例化");
	}
	
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public Set<Object> getSet() {
		return set;
	}
	public void setSet(Set<Object> set) {
		this.set = set;
	}
	public Map<Object, Object> getMap() {
		return map;
	}
	public void setMap(Map<Object, Object> map) {
		this.map = map;
	}
	public Properties getProps() {
		return props;
	}
	public void setProps(Properties props) {
		this.props = props;
	}
	
}
