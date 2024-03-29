package com.ljt.study.pp.retry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Objects;

/**
 * @author jtli3
 * @date 2022-03-29 19:36
 */
@Slf4j
public class RetryService {

    @Retryable(value = {IOException.class, IllegalArgumentException.class}, maxAttempts = 3,
            listeners = "customRetryListener",
//            interceptor = "customMethodInterceptor",
            backoff = @Backoff(value = 500, multiplier = 1.5))
    public String retryable(String param) throws IOException {
        log.info("retryable: {}", param);

        Objects.requireNonNull(param, "空指针异常");

        Assert.isTrue(!"arg".equals(param), "IllegalArgumentException异常");

        if ("io".equals(param)) {
            throw new IOException("IO异常");
        }

        return Retryable.class.getName();
    }

    @CircuitBreaker(value = IllegalArgumentException.class)
    public String circuitBreaker(String param) {
        log.info("circuitBreaker: {}", param);

        Assert.isTrue(!"arg".equals(param), "IllegalArgumentException异常");

        return CircuitBreaker.class.getName();
    }

    @Recover
    public String recover(Throwable e, String param) throws Throwable {
        log.info("||| recover: {} -  {}", param, e.getMessage());
        throw e;
//        return Recover.class.getName();
    }

}
