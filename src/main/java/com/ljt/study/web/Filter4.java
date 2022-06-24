package com.ljt.study.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LiJingTang
 * @date 2022-06-24 10:40
 */
@Slf4j
@Component
public class Filter4 extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("我进来了 {} {}", this.getClass().getSimpleName(), RequestUtils.get());
        filterChain.doFilter(request, response);
        log.info("我出来了 {}, {}", this.getClass().getSimpleName(), RequestUtils.get());
    }

}
