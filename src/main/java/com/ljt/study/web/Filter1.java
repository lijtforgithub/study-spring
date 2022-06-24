package com.ljt.study.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author LiJingTang
 * @date 2022-06-24 10:25
 */
@Slf4j
@Component
public class Filter1 implements OrderedFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("我进来了 {} {}", this.getClass().getSimpleName(), RequestUtils.get());
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("我出来了 {}, {}", this.getClass().getSimpleName(), RequestUtils.get());
    }

    @Override
    public int getOrder() {
        return 100;
    }

}
