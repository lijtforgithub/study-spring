package com.ljt.study.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author LiJingTang
 * @date 2022-06-24 10:33
 */
@Slf4j
@WebFilter
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("我进来了 {} {}", this.getClass().getSimpleName(), RequestUtils.get());
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("我出来了 {}, {}", this.getClass().getSimpleName(), RequestUtils.get());
    }

}
