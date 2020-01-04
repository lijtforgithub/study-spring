package com.ljt.study.ioc.di.xml;

/**
 * @author LiJingTang
 * @date 2020-01-04 11:32
 */
public class SetterBean {

    private String explain;
    private NestedBean bean;

    public SetterBean() {
        System.out.println(this.getClass().getName() + "无参构造方法实例化");
    }

    public SetterBean(String explain) {
        System.out.println(this.getClass().getName() + "有参构造方法实例化: 传入参数的值explain=" + explain + "， 创建出对象的值this.explain=" + this.explain);
    }

    public static SetterBean createInstance(NestedBean bean, String explain) {
        SetterBean diBean = new SetterBean();

        diBean.bean = bean;
        diBean.explain = explain;
        System.out.println("	基于Setter注入依赖：" + diBean.explain);

        return diBean;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
        System.out.println("	基于Setter注入依赖：" + this.explain);
    }

    public void setBeanName(String beanName) {
        System.out.println("	基于Setter注入依赖的Bean名字为：" + beanName);
    }

    public NestedBean getBean() {
        return bean;
    }

    public void setBean(NestedBean bean) {
        this.bean = bean;
    }

    static class NestedBean {

        public NestedBean() {
            System.out.println(this.getClass().getName() + "无参构造方法实例化");
        }

        public NestedBean(String info) {
            System.out.println(this.getClass().getName() + "有参构造方法实例化  " + info);
        }
    }

}
