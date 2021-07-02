package com.ljt.study.ioc.di.annotation;

import com.ljt.study.AbstractTest;
import com.ljt.study.ioc.di.annotation.qualifier.CustomQualifierBean;
import com.ljt.study.ioc.di.annotation.qualifier.MovieRecommender;
import com.ljt.study.ioc.di.annotation.qualifier.QualifierBean;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * @author LiJingTang
 * @date 2021-07-02 16:01
 */
class DITest extends AbstractTest {

    @Test
    void beans() {
        setApplicationContext("beans");

        AnnotationBean annotationBean = applicationContext.getBean("annotationBean", AnnotationBean.class);
        System.out.println(annotationBean.getPerson().getName());
    }

    @Test
    void testQualifierBean() {
        setApplicationContext("qualifier");
//		applicationContext = new AnnotationConfigApplicationContext(ContextConfigQualifier.class);

        QualifierBean qualifierBean = applicationContext.getBean("qualifierBean", QualifierBean.class);
        System.out.println(qualifierBean.getPrimaryBean().getData());
        CustomQualifierBean customBean = applicationContext.getBean("customBean", CustomQualifierBean.class);
        System.out.println(customBean.getFirstPrimaryBean().getData() + " - " + customBean.getSecondPrimaryBean().getData() + " - " + customBean.getOfflinePrimaryBean().getData());

        MovieRecommender movieRecommender = applicationContext.getBean("movieRecommender", MovieRecommender.class);
        System.out.println("ActionDvdCatalog: " + movieRecommender.getActionDvdCatalog());
        System.out.println("ActionVhsCatalog: " + movieRecommender.getActionVhsCatalog());
        System.out.println("ComedyVhsCatalog: " + movieRecommender.getComedyVhsCatalog());
        System.out.println("ComedyBluRayCatalog: " + movieRecommender.getComedyBluRayCatalog());
    }

    @Test
    void resource() {
        setApplicationContext("resource");
        ResourceBean resourceBean = applicationContext.getBean("resourceBean", ResourceBean.class);
        System.out.println(resourceBean.getBean().getData());
        ((AbstractApplicationContext) applicationContext).registerShutdownHook();
    }

    @Test
    void named() {
        setApplicationContext("named");

        NamedBean namedBean = applicationContext.getBean("namedBean", NamedBean.class);
        System.out.println(namedBean.getPrimaryBean().getData());
    }

    @SuppressWarnings("resource")
    @Test
    void testConfig() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("com.ljt.core.annotation.qualifier");
        ctx.refresh();
        System.out.println(ctx.getApplicationName());
    }

}
