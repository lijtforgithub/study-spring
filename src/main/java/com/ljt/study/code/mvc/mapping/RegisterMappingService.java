package com.ljt.study.code.mvc.mapping;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;

/**
 * @author LiJingTang
 * @date 2021-12-09 16:59
 */
@Slf4j
@Service
class RegisterMappingService {

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @SneakyThrows
    @PostConstruct
    public void init() {
        // handlerMapping.unregisterMapping()
        handlerMapping.registerMapping(RequestMappingInfo.paths("/rm/register").methods(RequestMethod.GET).build(),
                this, this.getClass().getMethod("register"));
        log.info("注册接口成功");
    }

    @ResponseBody
    public String register() {
        return "手动注册Http接口";
    }

}
