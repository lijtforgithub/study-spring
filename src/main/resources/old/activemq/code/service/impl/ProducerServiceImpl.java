package com.ljt.study.jms.service.impl;
 
import java.io.Serializable;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.ljt.study.jms.service.ProducerService;
 
/**
 * @author LiJingTang
 * @version 2015年8月19日 下午6:52:22
 */
@Service
public class ProducerServiceImpl implements ProducerService {
 
	@Autowired
    private JmsTemplate jmsTemplate;
	@Autowired(required=false)
	@Qualifier("responseQueue")
    private Destination responseDestination; 
    
    public void sendMessage(Destination destination, final String message) {
        System.err.println(this.getClass().getSimpleName() + "生产者发送消息：" + message);
        
        jmsTemplate.send(destination, new MessageCreator() {
        	@Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
    }
    
	@Override
	public void sendResponseMessage(Destination destination, final String message) {
		System.err.println(this.getClass().getSimpleName() + "生产者发送消息：" + message);
	        
        jmsTemplate.send(destination, new MessageCreator() {
        	@Override
            public Message createMessage(Session session) throws JMSException {  
                TextMessage textMessage = session.createTextMessage(message);  
                textMessage.setJMSReplyTo(responseDestination);
                
                return textMessage;  
            }  
        });  
	}
	
	/**
	 *	没有使用MessageConverter的时候我们需要new一个MessageCreator接口对象，然后在其抽象方法createMessage内部使用session创建一个对应的消息。
	 *	在使用了MessageConverter的时候我们在使用JmsTemplate进行消息发送时只需要调用其对应的convertAndSend方法即可
	 */
	@Override
	public void sendConverterMessage(Destination destination, final Serializable obj) {
		System.err.println(this.getClass().getSimpleName() + "生产者发送消息：" + obj);
		
		jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				ObjectMessage message = session.createObjectMessage(obj);
				
				message.setIntProperty("AccountID", 1234);
		        message.setJMSCorrelationID("123-00001");
				
				return message;
			}
		});
		
//		jmsTemplate.convertAndSend(destination, obj);
	}
	
}
