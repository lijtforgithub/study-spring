package com.ljt.study.reqlog;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author jtli3
 * @date 2022-01-11 18:59
 */
@Slf4j
class RestTemplateBeanPostProcessor implements BeanPostProcessor {

    private static final Map<Class<?>, Function<Object, RestTemplate>> REST_MAP;

    static {
        REST_MAP = Maps.newHashMapWithExpectedSize(2);
    }

    @Autowired
    private RestTemplateInterceptor restTemplateInterceptor;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Function<Object, RestTemplate> fun = REST_MAP.get(bean.getClass());

        if (Objects.nonNull(fun)) {
            RestTemplate restTemplate = fun.apply(bean);
            if (Objects.nonNull(restTemplate)) {
                List<ClientHttpRequestInterceptor> list = new ArrayList<>(restTemplate.getInterceptors());
                list.add(restTemplateInterceptor);
                restTemplate.setInterceptors(list);
                log.info("{} RestTemplate拦截器 {} ", beanName, restTemplate.getInterceptors());
            }
        }

        return bean;
    }

}
