package com.ljt.study.jms.annotation;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.adapter.JmsResponse;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class MessageListenerService {
	
    @JmsListener(containerFactory="jmsContainerFactory", destination="destination.queue.annotation")
    public void receiveMessage(String message) {
    	System.out.println(this.getClass().getName() + "接收到一条消息..." + message);
    }
    
    @JmsListener(containerFactory="jmsContainerFactory", destination="destination.queue.annotation.response")
    @SendTo("destination.queue.annotation")
    public String receiveMessageAndResponse(String message) {
    	System.out.println(this.getClass().getName() + "接收到一条消息..." + message);
    	
    	return "测试JMS注解MessageListenerAdapter返回的消息";
    }
    
    /**
     * 返回额外的头信息
     * @version 2015年10月1日 下午5:13:57
     */
    @JmsListener(destination="destination.queue.annotation.response.obj")
    @SendTo("destination.queue.annotation.obj")
    public Message<Object> receive(Object obj) {
        return MessageBuilder
                .withPayload(obj)
                .setHeader("code", 1234)
                .build();
    }
    
    /**
     * 运行时设置相应目的队列
     * @version 2015年10月1日 下午5:13:15
     */
    @JmsListener(destination="destination.queue.annotation.setup")
    public JmsResponse<Message<Object>> processOrder(Object obj) {
        Message<Object> response = MessageBuilder
                .withPayload(obj)
                .setHeader("code", 1234)
                .build();
        
        return JmsResponse.forQueue(response, "destination.queue.annotation.obj");
    }
    
}