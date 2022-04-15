package com.ljt.study.reqlog.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiJingTang
 * @date 2022-02-27 11:52
 */
@Slf4j
@Configuration
@ConditionalOnProperty(value = "logging.point.enabled", havingValue = "true", matchIfMissing = true)
public class LogConfig {

    @Bean
    LogAspect logAspect() {
        log.info("加载埋点日志切面");
        return new LogAspect();
    }

}
