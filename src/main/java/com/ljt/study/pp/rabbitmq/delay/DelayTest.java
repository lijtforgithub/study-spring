package com.ljt.study.pp.rabbitmq.delay;

import java.text.MessageFormat;
import java.util.concurrent.CountDownLatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LiJingTang
 * @date 2019年9月4日 上午10:07:23
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DelayConfig.class)
public class DelayTest {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 消息上设置ttl
     */
    @Test
    public void testTTLMsg() throws InterruptedException {
        MessageConsumer.cdLatch = new CountDownLatch(1);
        
        int t = 1 * 30;
        String message = MessageFormat.format("我是一个延迟【{0, number, #}】秒消费的消息", t);
        rabbitTemplate.convertAndSend(DelayConfig.TTL_MSG_QUEUE, message, msg -> {
            msg.getMessageProperties().setExpiration(String.valueOf(t * 1000));
            return msg;
        });
        log.info("发送消息：{}", message);
        
        MessageConsumer.cdLatch.await();
    }
    
    /**
     * 多个消息 在消息上设置ttl
     * 如果第一个消息时间长后面时间短  也要等第一个消息被转发 后面的消息才能转发
     * 所有要同一延迟时间用一个队列
     */
    @Test
    public void testTTLMutilMsg() throws InterruptedException {
        int count = 3;
        MessageConsumer.cdLatch = new CountDownLatch(count);
        
        for (int i = count; i > 0; i--) {
            int t = i * 10;
            String message = MessageFormat.format("我是一个设置延迟【{0, number, #}】秒消费的消息", t);
            rabbitTemplate.convertAndSend(DelayConfig.TTL_MSG_QUEUE, message, msg -> {
                msg.getMessageProperties().setExpiration(String.valueOf(t * 1000));
                return msg;
            });
            log.info("发送消息：{}", message);
        }
        
        MessageConsumer.cdLatch.await();
    }
    
    /**
     * 队列上设置ttl
     */
    @Test
    public void testTTLQueue() throws InterruptedException {
        MessageConsumer.cdLatch = new CountDownLatch(1);
        
        String message = MessageFormat.format("我是一个延迟【{0, number, #}】秒队列上的消息", DelayConfig.TTL_QUEUE_SED);
        rabbitTemplate.convertAndSend(DelayConfig.TTL_QUEUE, message);
        log.info("发送消息：{}", message);
        
        MessageConsumer.cdLatch.await();
    }
    
    /**
     * 消息和队列同时设置ttl
     */
    @Test
    public void testTTLQueueMsg() throws InterruptedException {
        int t = (int) (DelayConfig.TTL_QUEUE_SED * 2);
        String message = MessageFormat.format("我是一个延迟【{0, number, #}】秒队列上设置了延迟【{1, number, #}】秒消费的消息", DelayConfig.TTL_QUEUE_SED, t);
        rabbitTemplate.convertAndSend(DelayConfig.TTL_QUEUE, message, msg -> {
            msg.getMessageProperties().setExpiration(String.valueOf(t * 1000));
            return msg;
        });
        log.info("发送消息：{}", message);
    }
    
}
