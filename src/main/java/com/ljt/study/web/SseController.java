package com.ljt.study.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author LiJingTang
 * @date 2021-04-27 17:52
 */
@Slf4j
@RestController
@RequestMapping("/web/sse")
public class SseController {

    @GetMapping(produces = "text/event-stream;charset=utf-8")
    public String index() {
        log.info("来了 老弟 {}", Thread.currentThread().getName());

        return "data:" + LocalDateTime.now().toString() + " \n\n";
    }

}
