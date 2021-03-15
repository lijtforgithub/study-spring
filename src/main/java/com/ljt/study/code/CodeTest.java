package com.ljt.study.code;

import com.ljt.study.AbstractTest;
import com.ljt.study.entity.User;
import org.junit.jupiter.api.Test;

/**
 * @author LiJingTang
 * @date 2021-03-12 09:50
 */
public class CodeTest extends AbstractTest {

    public static void main(String[] args) {
        System.setProperty("ctx", "context");
        new MyClassPathXmlApplicationContext("classpath:code/application-${ctx}.xml");
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
