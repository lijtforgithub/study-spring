package com.ljt.study.pp.retry;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author jtli3
 * @date 2022-03-29 19:36
 */
@Slf4j
@SpringJUnitConfig(RetryConfig.class)
class RetryTest {

    @Autowired
    private RetryService retryService;

    @SneakyThrows
    @Test
    void retryable() {
        log.info(retryService.retryable("arg"));
    }

    @Test
    void circuitBreaker() {
        for (int i = 0; i < 5; i++) {
            retryService.circuitBreaker("arg");
        }
    }

}
