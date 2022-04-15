package com.ljt.study.reqlog;

import com.ljt.study.reqlog.retry.RetryRcAdvisor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jtli3
 * @date 2022-01-10 14:22
 */
@Slf4j
@ServletComponentScan
@Configuration
class RequestLogConfig {

    @Bean
    RestTemplateBeanPostProcessor restTemplateBeanPostProcessor() {
        return new RestTemplateBeanPostProcessor();
    }

    @Bean
    RetryRcAdvisor retryRcAdvisor() {
        return new RetryRcAdvisor();
    }

}
