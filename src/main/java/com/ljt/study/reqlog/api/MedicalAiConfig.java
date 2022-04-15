package com.ljt.study.reqlog.api;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author jtli3
 * @date 2022-03-15 10:27
 */
@Configuration
public class MedicalAiConfig {

    @Bean
    MedicalAiProperties medicalAiProperties() {
        return new MedicalAiProperties();
    }

    @Bean
    MedicalAiRestTemplateWrapper medicalAiRestTemplateWrapper() {
        return new MedicalAiRestTemplateWrapper();
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 100)
    MedicalAiExceptionInterceptor exceptionInterceptor() {
        return new MedicalAiExceptionInterceptor();
    }

}
