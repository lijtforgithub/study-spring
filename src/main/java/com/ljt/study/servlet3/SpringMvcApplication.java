package com.ljt.study.servlet3;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * springmvc注解用法 不用springboot
 *
 * @author LiJingTang
 * @date 2021-07-18 12:41
 */
class SpringMvcApplication extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 父容器
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    /**
     * 子容器 mvc
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

}

@ComponentScan(excludeFilters = @ComponentScan.Filter(Controller.class), useDefaultFilters = false)
class RootConfig {

}

@EnableWebMvc
@ComponentScan(includeFilters = @ComponentScan.Filter(Controller.class), useDefaultFilters = false)
class MvcConfig implements WebMvcConfigurer {

    /**
     * 处理静态资源不被拦截
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
