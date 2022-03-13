package com.ljt.study.stomp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageDeliveryException;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author LiJingTang
 * @date 2022-03-11 10:10
 */
@Slf4j
@Controller
public class StompController {

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/message")
    public String message(Message<String> message) {
        log.info("--- @MessageMapping {}", message.getPayload());
        return MessageMapping.class.getSimpleName() + " : " + message.getPayload();
    }

    @SubscribeMapping("/topic/message/{id}")
    public String subscribe(@DestinationVariable String id){
        log.info("--- @SubscribeMapping {}", id);
        return SubscribeMapping.class.getSimpleName() + " : " + id;
    }


    /**
     * 如果您希望此类方法更全局地应用 (跨控制器) , 则可以在标有 @ControllerAdvice 的类中声明它们
     */
    @MessageExceptionHandler
    public String handleException(Exception e) {
        log.error("消息处理异常", e);
        return "消息处理异常" + e.getMessage();
    }

    @MessageExceptionHandler
    public void handleException(MessageDeliveryException e) {
        log.error("消息发送异常 {}", new String(e.getMessage().getBytes()));
    }


    @ResponseBody
    @GetMapping("/stomp/send")
    public String sendMsg(String msg) {
        try {
            template.convertAndSend("/topic/message", msg);
            return "推送消息成功：" + msg;
        } catch (MessageDeliveryException e) {
            log.error("消息发送异常 {}", new String(e.getMessage().getBytes()));
            return "推送消息异常：" + e.getMessage();
        }
    }

}

