package com.ljt.study.pp.retry;

import com.ljt.study.entity.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryListener;
import org.springframework.retry.policy.TimeoutRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.Assert;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jtli3
 * @date 2022-03-29 18:54
 */
@Slf4j
class RetryApiTest {

    @SneakyThrows
    @Test
    void exception() {
        RetryTemplate template = new RetryTemplate();
        template.setListeners(new RetryListener[] {new CustomRetryListener()});
        AtomicInteger count = new AtomicInteger();

        User user = template.execute((RetryCallback<User, IllegalArgumentException>) context -> {
            Assert.isTrue(count.getAndIncrement() > 1, "重试");

            User u = new User();
            u.setName("RETRY");
            return u;
        });

        log.info(user.toString());
    }

    @SneakyThrows
    @Test
    void timeout() {
        RetryTemplate template = new RetryTemplate();
        TimeoutRetryPolicy policy = new TimeoutRetryPolicy();
        // 指定时间内一直重试
        policy.setTimeout(5L);
        template.setRetryPolicy(policy);

        User user = template.execute((RetryCallback<User, IllegalArgumentException>) context -> {
            Assert.isTrue(1 != 1, "重试");
            return new User();
        });

        log.info(user.toString());
    }

}
