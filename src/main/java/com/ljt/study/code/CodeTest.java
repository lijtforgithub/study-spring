package com.ljt.study.code;

import com.ljt.study.AbstractTest;
import com.ljt.study.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author LiJingTang
 * @date 2021-03-12 09:50
 */
public class CodeTest extends AbstractTest {

    public static void main(String[] args) {
        System.setProperty("ctx", "context");
        MyClassPathXmlApplicationContext context = new MyClassPathXmlApplicationContext("classpath:code/application-${ctx}.xml");
        for (BeanFactoryPostProcessor bfpp : context.getBeanFactoryPostProcessors()) {
            System.out.println(bfpp.getClass());
        }
    }

    @Test
    public void testBfpp() {
        new MyClassPathXmlApplicationContext("classpath:code/bfpp.xml");
    }

    @Test
    public void testTag() {
        setApplicationContext("tag");
        System.out.println(applicationContext.getBean(User.class));
    }

    @Test
    public void testEditor() {
        setApplicationContext("editor");
        System.out.println(applicationContext.getBean(User.class));
    }

    @Test
    public void testAware() {
        setApplicationContext("aware");
    }

}
