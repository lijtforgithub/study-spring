package com.ljt.study.slice.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author LiJingTang
 * @date 2022-11-30 10:57
 */
@Configuration
public class ThreadPoolConfig {

    @Bean
    ThreadPoolTaskExecutor uploadTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("log-upload-");
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
        executor.setMaxPoolSize(executor.getCorePoolSize() * 3);
        executor.setQueueCapacity(1000);
        executor.initialize();
        return executor;
    }

}
