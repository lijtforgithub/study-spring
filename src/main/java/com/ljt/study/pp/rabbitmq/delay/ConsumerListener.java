package com.ljt.study.pp.rabbitmq.delay;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LiJingTang
 * @date 2019年9月4日 上午11:20:52
 */
@Slf4j
@Component
public class ConsumerListener implements MessageListener {
    
    static CountDownLatch cdLatch;

    @Override
    public void onMessage(Message message) {
        String header = JSON.toJSONString(message.getMessageProperties());
        log.debug("接收消息头信息：{}", header);
        log.info("接收消息内容：{}", new String(message.getBody()));
        
        if (Objects.nonNull(cdLatch)) {
            cdLatch.countDown();
        }
    }

}
