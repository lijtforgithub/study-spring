package com.ljt.study.code;

import com.ljt.study.AbstractTest;
import com.ljt.study.code.bfpp.supplier.BfppSupplierConfig;
import com.ljt.study.code.bpp.Bpp;
import com.ljt.study.code.bpp.instantiation.InstantiationAwareBppConfig;
import com.ljt.study.code.cycle.A;
import com.ljt.study.code.populate.PopulateBean;
import com.ljt.study.code.replace.OriginalHello;
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

    @Test
    public void testReplaceMethod() {
        setApplicationContext("replace-method");
        OriginalHello hello = applicationContext.getBean(OriginalHello.class);
        hello.sayHello();
        hello.sayHello("璟瑜");
    }

    @Test
    public void testBpp() {
        setApplicationContext("bpp");
        Bpp bean = applicationContext.getBean(Bpp.class);
        bean.doSomething();
    }

    @Test
    public void testInstantiationAwareBpp() {
        setApplicationContext(InstantiationAwareBppConfig.class);
        Bpp bean = applicationContext.getBean(Bpp.class);
        bean.doSomething();
    }

    @Test
    public void testBfppSupplier() {
        setApplicationContext(BfppSupplierConfig.class);
        User bean = applicationContext.getBean(User.class);
        System.out.println(bean.getName());
    }

    @Test
    public void testPopulate() {
        setApplicationContext("populate");
        PopulateBean bean = applicationContext.getBean(PopulateBean.class);
        System.out.println(bean);
    }

    @Test
    public void testCycle() {
        setApplicationContext("cycle");
        A bean = applicationContext.getBean(A.class);
        System.out.println(bean.getB().getClass());
    }

}
