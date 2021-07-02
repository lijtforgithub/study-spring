package com.ljt.study.jms.listener;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import com.ljt.study.entity.Email;
 
/**
 * @author LiJingTang
 * @version 2015年8月19日 下午8:35:23
 */
public class ConsumerListener {
 
    public void handleMessage(String message) {
        System.out.println(this.getClass().getName() + ".handleMessage接收到一个纯文本消息：" + message);
    }
    
//    public void receiveMessage(String message) {
//        System.out.println(this.getClass().getName() + ".receiveMessage接收到一个纯文本消息：" + message);
//    }
    
    /**
     * 当我们用于处理接收到的消息的方法的返回值不为空的时候，Spring会自动将它封装为一个JMS Message，然后自动进行回复。
     */
    public String receiveMessage(String message) {  
        System.out.println(this.getClass().getName() + ".receiveMessage接收到一个纯文本消息：" + message);  
        
        return this.getClass().getName() + "的MessageListenerAdapter自动返回的消息";  
    }
    
    public void receiveMessage(Email email) {  
        System.out.println(this.getClass().getName() + ".receiveMessage接收到一个ObjectMessage消息：" + email);  
    }
    
    /**
     * 设置MessageListenerAdapter的messageConverter属性为空
     * MessageListener的处理器方法参数类型就应该是Jms Message或对应的Jms Message子类型了，不然就会调用不到对应的处理方法了
     */
    public void receiveMessage(ObjectMessage message) throws JMSException {  
       System.out.println("设置MessageListenerAdapter的messageConverter属性为空调用的处理器方法" + message.getObject());  
    } 
    
}

