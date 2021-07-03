package com.ljt.study.aop.perthis;

import com.ljt.study.aop.service.GoodsService;
import com.ljt.study.aop.service.OrderService;
import com.ljt.study.aop.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

/**
 * @author LiJingTang
 * @date 2021-07-03 16:04
 */
@Configuration
@EnableAspectJAutoProxy
public class PerThisConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public PerThisAspect perThisAspect() {
        return new PerThisAspect();
    }

    @Bean
    public GoodsService goodsService() {
        return new GoodsService();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl();
    }

}
