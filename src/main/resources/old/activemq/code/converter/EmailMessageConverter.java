package com.ljt.study.jms.converter;
import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
 


import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
 
/**
 * @author LiJingTang
 * @version 2015年8月19日 下午9:47:40
 */
@Component
public class EmailMessageConverter implements MessageConverter {
 
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        return session.createObjectMessage((Serializable) object);
    }
 
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        ObjectMessage objMessage = (ObjectMessage) message;
        
        return objMessage.getObject();
    }
    
}
