package com.ljt.study.code;

import com.ljt.study.AbstractTest;
import com.ljt.study.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;

/**
 * ComponentScanBeanDefinitionParser
 * ConfigurationClassPostProcessor
 * AopContext
 *
 * @author LiJingTang
 * @date 2021-03-12 09:50
 */
public class CodeTest extends AbstractTest {

    @Test
    public void testContext() {
        System.setProperty("ctx", "context");
        ApplicationContext context = new MyClassPathXmlApplicationContext("classpath:code/application-${ctx}.xml");
        System.out.println(context);
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

    @Test
    public void testConvert() {
        setApplicationContext("convert");
        ConversionService conversionService = applicationContext.getBean(ConversionService.class);
        User user = conversionService.convert("1_璟瑜", User.class);
        System.out.println(user);
    }

}
