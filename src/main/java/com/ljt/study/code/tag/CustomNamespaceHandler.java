package com.ljt.study.code.tag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 自定义标签命名空间
 *
 * @author LiJingTang
 * @date 2021-03-14 10:37
 */
public class CustomNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
    }

}
