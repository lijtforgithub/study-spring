package com.ljt.study.aop;

import com.ljt.study.AbstractTest;
import com.ljt.study.aop.aspectj.ConfigurableBean;
import com.ljt.study.aop.aspectj.ConfigurableConfig;
import com.ljt.study.aop.introduction.IntroductionConfig;
import com.ljt.study.aop.introduction.IntroductionService;
import com.ljt.study.aop.perthis.PerThisConfig;
import com.ljt.study.aop.service.GoodsService;
import com.ljt.study.aop.service.OrderService;
import com.ljt.study.aop.service.ParameterService;
import org.junit.jupiter.api.Test;

/**
 * @author LiJingTang
 * @date 2021-07-02 18:08
 */
class AopTest extends AbstractTest {

    @Test
    void bean() {
        setApplicationContext("bean");
        GoodsService goodsService = applicationContext.getBean("goodsService", GoodsService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        goodsService.saveGoods();
        orderService.saveOrder();
    }

    @Test
    void param() {
        setApplicationContext("bean");
        ParameterService parameterService = applicationContext.getBean("parameterService", ParameterService.class);
        parameterService.deleteParam(0L, "两个参数");
        parameterService.saveParam();
    }

    @Test
    void adviceOrder() {
        setApplicationContext("bean");
        GoodsService goodsService = applicationContext.getBean("goodsService", GoodsService.class);
        goodsService.orderMethod();
    }

    @Test
    void introduction() {
        setApplicationContext(IntroductionConfig.class);
        ParameterService parameterService = applicationContext.getBean("parameterService", ParameterService.class);
        IntroductionService introductionService = (IntroductionService) parameterService;
        introductionService.introduction();
    }

    @Test
    void perThis() {
        setApplicationContext(PerThisConfig.class);

        GoodsService goodsService = applicationContext.getBean("goodsService", GoodsService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
        goodsService.saveGoods();
        orderService.saveOrder();
    }

    @Test
    void aspectj() {
        setApplicationContext(ConfigurableConfig.class);
        ConfigurableBean domain = new ConfigurableBean();

        System.out.println(domain + " - " + domain.getGoodsService());
    }

}
