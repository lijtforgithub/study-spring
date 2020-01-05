package com.ljt.study.util;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.http.HttpServlet;

/**
 * @author LiJingTang
 * @date 2020-01-04 20:04
 */
public class SpringUtils {

    private ApplicationContext applicationContext;
    private WebApplicationContext webApplicationContext;

    // 获取WebApplicationContext
    {
        this.webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        this.webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(new HttpServlet() {
            private static final long serialVersionUID = -7305295147377602950L;
        }.getServletContext());

        this.webApplicationContext = new XmlWebApplicationContext();
    }

    // 获取ApplicationContext
    {
        this.applicationContext = new ClassPathXmlApplicationContext(new String[]{"services.xml", "daos.xml"});
        this.applicationContext = new FileSystemXmlApplicationContext(new String[]{"services.xml", "daos.xml"});
    }

    // bean的定义还包含如何创建特定bean的信息，ApplicationContext的实现类允许用户将容器外部创建的已有对象的注册
    {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) this.applicationContext.getParentBeanFactory();
        beanFactory.registerSingleton("beanName", new Object());
        beanFactory.registerBeanDefinition("beanName", new RootBeanDefinition());
    }

}
