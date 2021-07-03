package com.ljt.study.aop.aspectj;

import com.ljt.study.aop.service.GoodsService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * 注解@Configurable标记了一个类可以通过Spring-driven方式来配置
 * 用于解决非Spring容器管理的Bean中却依赖Spring Bean的场景，也就是说Bean A依赖了一个Spring的Bean B，但是A不是Spring 得Bean所以无法进行属性注入拿不到B的情况
 *
 * @author LiJingTang
 * @version 2015年9月18日下午3:33:10
 */
// Spring会查找名字为domain的bean定义，并使用它作定义来配置一个新的 ConfigurableBean实例。
// dependencyCheck=true Spring会在配置结束后校验（除了primitives和collections类型） 所有的属性是否都被设置
@Configurable(value = "domain", autowire = Autowire.BY_TYPE, dependencyCheck = true, preConstruction = true) // preConstruction=true
@Getter
@Setter
public class ConfigurableBean {

    public String check;
    private GoodsService goodsService;

    public ConfigurableBean() {
        System.out.println(this.getClass().getName() + "无参构造方法实例化...");
    }

    public void save() {
        goodsService.saveGoods();
    }

}
