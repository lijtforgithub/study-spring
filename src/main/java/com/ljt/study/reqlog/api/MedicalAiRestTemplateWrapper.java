package com.ljt.study.reqlog.api;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import javax.annotation.PostConstruct;

/**
 * @author jtli3
 * @date 2022-02-24 9:48
 */
@Slf4j
public class MedicalAiRestTemplateWrapper {

    private RestTemplate restTemplate;

    @Value("${restTemplate.ConnectTimeout:2000}")
    private int connectTimeout;
    @Value("${restTemplate.ReadTimeout:2000}")
    private int readTimeout;

    @Autowired
    private MedicalAiProperties medicalAiProperties;
    @Autowired
    private MedicalAiExceptionInterceptor exceptionInterceptor;

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(medicalAiProperties.getConnectTimeout() > 0 ? medicalAiProperties.getConnectTimeout() : connectTimeout);
        factory.setReadTimeout(medicalAiProperties.getReadTimeout() > 0 ? medicalAiProperties.getReadTimeout() : readTimeout);
        restTemplate.setRequestFactory(factory);

        String url = "http://" + medicalAiProperties.getServiceName();
        if (StringUtils.isNotBlank(url)) {
            DefaultUriBuilderFactory uriBuilder = new DefaultUriBuilderFactory(url);
            uriBuilder.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.TEMPLATE_AND_VALUES);
            restTemplate.setUriTemplateHandler(uriBuilder);

            log.info("设置 URI 模板：{}", url);
        }

        restTemplate.getInterceptors().addAll(Lists.newArrayList(exceptionInterceptor));
//        if (Objects.nonNull(loadBalancerInterceptor)) {
//            restTemplate.getInterceptors().add(loadBalancerInterceptor);
//            log.info("设置 LoadBalancerInterceptor");
//        }
    }

}
