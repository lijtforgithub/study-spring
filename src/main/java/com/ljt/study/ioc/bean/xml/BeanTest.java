package com.ljt.study.ioc.bean.xml;

import com.ljt.study.AbstractTest;
import com.ljt.study.ioc.bean.xml.extend.Chinese;
import com.ljt.study.ioc.bean.xml.scope.PrototypeBean;
import com.ljt.study.ioc.bean.xml.scope.SingletonBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * @author LiJingTang
 * @date 2020-01-04 10:43
 */
class BeanTest extends AbstractTest {

    @Test
    void beans() {
        setApplicationContext("beans");
        printBeanDefinition();

        Bean bean1 = applicationContext.getBean("beanId", Bean.class);
        Bean bean2 = applicationContext.getBean("aliasBeanName", Bean.class);
        System.out.println("测试在bean定义外面起别名：" + (bean1 == bean2));
        System.out.println(bean1 == applicationContext.getBean("b1", Bean.class));
    }

    @Test
    void beanAttr() {
        setApplicationContext("bean-attr");

        // null和空字符串
        Bean emptyAndNull = applicationContext.getBean("emptyAndNull", Bean.class);
        System.out.println("null和空字符串赋值: empty:" + emptyAndNull.getEmptyString() + ", null:" + emptyAndNull.getNullString());

        // 复合属性名称
        CompoundBean compoundBean = applicationContext.getBean("compoundBeanId", CompoundBean.class);
        System.out.println("复合属性名称（bean.emptyString）赋值: empty:" + compoundBean.getBean().getEmptyString());
    }

    @Test
    void dependsOn() {
        setApplicationContext("depends-on");
    }

    @Test
    void lazy() {
        setApplicationContext("lazy");

        applicationContext.getBean("notSingleton", LazyBean.class);
        applicationContext.getBean("lazyBean", LazyBean.class);
    }

    @Test
    void scope() {
        setApplicationContext("scope");

        // 默认
        Bean defaultBean1 = this.applicationContext.getBean("defaultBean", Bean.class);
        Bean defaultBean2 = this.applicationContext.getBean("defaultBean", Bean.class);
        System.out.println("默认两个对象相等：" + (defaultBean1 == defaultBean2));

        // singleton
        SingletonBean singletonBean1 = applicationContext.getBean("singletonBean", SingletonBean.class);
        SingletonBean singletonBean2 = applicationContext.getBean("singletonBean", SingletonBean.class);
        System.out.println("singleton两个对象相等：" + (singletonBean1 == singletonBean2));

        // prototype
        PrototypeBean prototypeBean1 = applicationContext.getBean("prototypeBean", PrototypeBean.class);
        PrototypeBean prototypeBean2 = applicationContext.getBean("prototypeBean", PrototypeBean.class);
        System.out.println("prototype两个对象相等：" + (prototypeBean1 == prototypeBean2));

        System.out.println("开始调用销毁回调方法，先实例化的后调用，prototype的bean不会调用...");
//		((ClassPathXmlApplicationContext) applicationContext).destroy();
        ((AbstractApplicationContext) applicationContext).registerShutdownHook();
    }

    @Test
    void extend() {
        setApplicationContext("extends");

        Chinese chinese = applicationContext.getBean("chinese", Chinese.class);
        System.out.println(chinese);
        chinese = applicationContext.getBean("chineseClass", Chinese.class);
        System.out.println(chinese);
    }

}
