package com.ljt.study.code;

import com.ljt.study.AbstractTest;
import com.ljt.study.code.bfpp.supplier.BfppSupplierConfig;
import com.ljt.study.code.bpp.Bpp;
import com.ljt.study.code.bpp.instantiation.InstantiationAwareBppConfig;
import com.ljt.study.code.populate.PopulateBean;
import com.ljt.study.code.replace.OriginalHello;
import com.ljt.study.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;

import java.util.Objects;

/**
 * ComponentScanBeanDefinitionParser
 * ConfigurationClassPostProcessor
 * AopContext
 *
 * @author LiJingTang
 * @date 2021-03-12 09:50
 */
@Slf4j
class CodeTest extends AbstractTest {

    @Test
    void context() {
        System.setProperty("ctx", "context");
        ApplicationContext context = new MyClassPathXmlApplicationContext("classpath:code/application-${ctx}.xml");
        log.info(context.toString());
    }

    @Test
    void bfpp() {
        new MyClassPathXmlApplicationContext("classpath:code/bfpp.xml");
    }

    @Test
    void tag() {
        setApplicationContext("tag");
        log.info(applicationContext.getBean(User.class).toString());
    }

    @Test
    void editor() {
        setApplicationContext("editor");
        log.info(applicationContext.getBean(User.class).toString());
    }

    @Test
    void aware() {
        setApplicationContext("aware");
    }

    @Test
    void convert() {
        setApplicationContext("convert");
        ConversionService conversionService = applicationContext.getBean(ConversionService.class);
        User user = conversionService.convert("1_璟瑜", User.class);
        log.info(Objects.requireNonNull(user).toString());
    }

    @Test
    void replaceMethod() {
        setApplicationContext("replace-method");
        OriginalHello hello = applicationContext.getBean(OriginalHello.class);
        hello.sayHello();
        hello.sayHello("璟瑜");
    }

    @Test
    void bpp() {
        setApplicationContext("bpp");
        Bpp bean = applicationContext.getBean(Bpp.class);
        bean.doSomething();
    }

    @Test
    void instantiationAwareBpp() {
        setApplicationContext(InstantiationAwareBppConfig.class);
        Bpp bean = applicationContext.getBean(Bpp.class);
        bean.doSomething();
    }

    @Test
    void bfppSupplier() {
        setApplicationContext(BfppSupplierConfig.class);
        User bean = applicationContext.getBean(User.class);
        log.info(bean.getName());
    }

    @Test
    void populate() {
        setApplicationContext("populate");
        PopulateBean bean = applicationContext.getBean(PopulateBean.class);
        log.info(bean.toString());
    }

    @Test
    void cycle() {
        setApplicationContext("cycle");
//        A bean = applicationContext.getBean(A.class);
//        log.info(bean.getB().getClass());
    }


    @Test
    void beanWrapper() {
        User user = new User();
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(user);
        bw.setPropertyValue("name", "张三");
        log.info(user.getName());
    }

}
