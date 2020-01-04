package com.ljt.study.ioc.di.xml;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author LiJingTang
 * @date 2020-01-04 11:30
 */
@Getter
@Setter
public class CollectionBean {

    private List<Object> list;
    private Set<Object> set;
    private Map<Object, Object> map;
    private Properties props;

    public CollectionBean() {
        System.out.println(this.getClass().getName() + "无参构造方法实例化");
    }

}
