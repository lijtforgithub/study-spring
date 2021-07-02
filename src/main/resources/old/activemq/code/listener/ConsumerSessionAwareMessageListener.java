package com.ljt.study.jms.listener;
 
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
 


import org.springframework.jms.listener.SessionAwareMessageListener;
 
/**
 * @author LiJingTang
 * @version 2015年9月9日 下午11:14:05
 */
public class ConsumerSessionAwareMessageListener implements SessionAwareMessageListener<TextMessage> {
 
    private Destination destination;
    
    public Destination getDestination() {
        return destination;
    }
    public void setDestination(Destination destination) {
        this.destination = destination;
    }
    
    @Override
    public void onMessage(TextMessage message, Session session) throws JMSException {
    	System.out.println(this.getClass().getName() + "接收到一条消息...：" + message.getText());
        
        MessageProducer producer = session.createProducer(destination);
        Message textMessage = session.createTextMessage(this.getClass().getName() + "回复消息给生产者的消息");
        producer.send(textMessage);
    }
    
}

