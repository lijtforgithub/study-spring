package com.ljt.study.jms.listener;
 
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.springframework.jms.support.converter.MessageConverter;

import com.ljt.study.entity.Email;
import com.ljt.study.jms.service.DBService;
 
/**
 * @author LiJingTang
 * @version 2015年8月19日 下午7:01:44
 */
public class ConsumerMessageListener implements MessageListener {
	
	private MessageConverter messageConverter;
	private DBService dbService;
	private int count = 0;
	
    public MessageConverter getMessageConverter() {
		return messageConverter;
	}
	public void setMessageConverter(MessageConverter messageConverter) {
		this.messageConverter = messageConverter;
	}
	
	@Override
	public void onMessage(Message message) {
		System.out.println(this.getClass().getName() + "接收到一条消息...");
		
        try {
        	if (message instanceof TextMessage) {
        		TextMessage textMsg = (TextMessage) message;
        		
        		if ("JMS-Transaction-Test".equals(textMsg.getText())) {
        			throw new RuntimeException("JMS 事物测试...");
        		} else if ("JMS-Transaction-JTA".equals(textMsg.getText())) {
        			this.dbService.saveData(textMsg.getText() + "_" + count);
        			
        			if (0 == count) {
        				count++;
        				throw new UnsupportedOperationException();
        			}
        		}
        		
        		System.out.println("	文本消息：" + textMsg.getText());
        	} else if (message instanceof ObjectMessage) {  
                ObjectMessage objMessage = (ObjectMessage) message;
                
                /*Object obj = objMessage.getObject();  
                Email email = (Email) obj; */
                
                Email email = (Email) this.messageConverter.fromMessage(objMessage);  
                System.out.println("	ObjectMessage消息：" + email);  
            }  
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
	
	public DBService getDbService() {
		return dbService;
	}
	public void setDbService(DBService dbService) {
		this.dbService = dbService;
	}
	
}