package com.ljt.study.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiJingTang
 * @date 2022-06-24 10:34
 */
@Slf4j
@Component
public class Filter3 extends AbstractRequestLoggingFilter {

    @Override
    protected boolean isIncludePayload() {
        log.info("我执行了 {} {}", this.getClass().getSimpleName(), RequestUtils.get());
        return true;
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {

    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {

    }

}
