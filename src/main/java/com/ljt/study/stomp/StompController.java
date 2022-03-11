package com.ljt.study.stomp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

/**
 * @author LiJingTang
 * @date 2022-03-11 10:10
 */
@Slf4j
@Controller
public class StompController {

    @SendTo("/topic/message")
    @MessageMapping("/message")
    public String message(String name) {
        log.info("--- @MessageMapping {}", name);
        return MessageMapping.class.getName();
    }

    @SubscribeMapping("/topic/message")
    public String subscribe(){
        log.info("--- @SubscribeMapping");
        return SubscribeMapping.class.getName();
    }

}

