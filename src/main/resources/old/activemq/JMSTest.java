package com.ljt.study.jms;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.ljt.study.entity.Email;
import com.ljt.study.jms.annotation.ContextConfigJMS;
import com.ljt.study.jms.annotation.ContextConfigJMSEndpoint;
import com.ljt.study.jms.listener.ConsumerMessageListener;
import com.ljt.study.jms.service.ProducerService;
import com.ljt.study.util.TestUtils;

/**
 * @author LiJingTang
 * @version 2015年8月19日 下午7:07:04
 */
public class JMSTest {

	private static final String MODULE = "/jms/";
	private static final String CONFIG_LISTENER = "listener";
	private static final String CONFIG_SESSION = "session";
	private static final String CONFIG_ADAPTER = "adapter";
	private static final String CONFIG_CONVERTER = "converter";
	private static final String CONFIG_TRANSACTION = "transaction";
	private static final String CONFIG_ANNOTATION = "annotation";
	private static final String CONFIG_JCA = "jca";
	private ApplicationContext applicationContext;
  
    @Test
    public void testQueue() {  
    	this.applicationContext = TestUtils.instanceContext(MODULE, CONFIG_LISTENER);
    	ProducerService producerService = this.applicationContext.getBean(ProducerService.class, "producerService");
    	Destination queue = this.applicationContext.getBean("queue", Destination.class);
    	
    	producerService.sendMessage(queue, "LiJingTang-JMS-Message-Queue");  
    }
    
    @Test
    public void testTopic() {  
    	this.applicationContext = TestUtils.instanceContext(MODULE, CONFIG_LISTENER);
    	ProducerService producerService = this.applicationContext.getBean(ProducerService.class, "producerService");
    	Destination queue = this.applicationContext.getBean("topic", Destination.class);
    	
    	producerService.sendMessage(queue, "LiJingTang-JMS-Message-Topic");  
    }
      
    @Test
    public void testSessionAwareMessageListener() {
    	this.applicationContext = TestUtils.instanceContext(MODULE, CONFIG_SESSION);
    	ProducerService producerService = this.applicationContext.getBean(ProducerService.class, "producerService");
    	Destination sessionAwareQueue = this.applicationContext.getBean("sessionAwareQueue", Destination.class);
    	
        producerService.sendMessage(sessionAwareQueue, "测试SessionAwareMessageListener");
        
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        System.err.println("实例化一个消息监听容器，开始接收返回给生产者的消息...");
        
        DefaultMessageListenerContainer continer = new DefaultMessageListenerContainer();
        continer.setConnectionFactory(this.applicationContext.getBean("connectionFactory", ConnectionFactory.class));
        continer.setDestination(this.applicationContext.getBean("queue", Destination.class));
        continer.setMessageListener(new ConsumerMessageListener());
        continer.initialize();
        continer.start();
    }
    
    @Test  
    public void testMessageListenerAdapter() { 
    	this.applicationContext = TestUtils.instanceContext(MODULE, CONFIG_ADAPTER);
    	ProducerService producerService = this.applicationContext.getBean(ProducerService.class, "producerService");
    	Destination adapterQueue = this.applicationContext.getBean("adapterQueue", Destination.class);
    	
//        producerService.sendMessage(adapterQueue, "测试MessageListenerAdapter");
        producerService.sendResponseMessage(adapterQueue, "测试MessageListenerAdapter自动返回消息");
    }
    
    @Test  
    public void testConverterObjectMessage() {  
    	this.applicationContext = TestUtils.instanceContext(MODULE, CONFIG_CONVERTER);
    	ProducerService producerService = this.applicationContext.getBean(ProducerService.class, "producerService");
    	Destination converterQueue = this.applicationContext.getBean("converterQueue", Destination.class);
        
        producerService.sendConverterMessage(converterQueue,  new Email("zhangsan@xxx.com", "主题", "内容"));  
    }
    
    @Test
    public void testTransaction() {  
    	this.applicationContext = TestUtils.instanceContext(MODULE, CONFIG_TRANSACTION);
    	ProducerService producerService = this.applicationContext.getBean(ProducerService.class, "producerService");
    	Destination queue = this.applicationContext.getBean("queue", Destination.class);
    	
    	producerService.sendMessage(queue, "JMS-Transaction-Test");  
    }
    
    /*@Test
    public void testTransaction_JTA() {  
    	this.applicationContext = TestUtils.instanceContext(MODULE, CONFIG_TRANSACTION);
    	ProducerService producerService = this.applicationContext.getBean(ProducerService.class, "producerService");
    	Destination queue = this.applicationContext.getBean("queue", Destination.class);
    	
    	producerService.sendMessage(queue, "JMS-Transaction-JTA"); 
    	
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }*/
    
    @Test
    public void testAnnation() {  
    	this.applicationContext = TestUtils.instanceContext(MODULE, CONFIG_ANNOTATION);
//    	this.applicationContext = new AnnotationConfigApplicationContext(ContextConfigJMS.class);
    	
    	ProducerService producerService = this.applicationContext.getBean(ProducerService.class, "producerService");
    	Destination annotationQueue = this.applicationContext.getBean("annotationQueue", Destination.class);
    	
    	producerService.sendMessage(annotationQueue, "使用JMS注解的侦听器");
    	
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void testEndpoint() {  
    	this.applicationContext = new AnnotationConfigApplicationContext(ContextConfigJMSEndpoint.class);
    	ProducerService producerService = this.applicationContext.getBean(ProducerService.class, "producerService");
    	Destination endpointQueue = this.applicationContext.getBean("endpointQueue", Destination.class);
    	
    	producerService.sendMessage(endpointQueue, "使用SimpleJmsListenerEndpoint注册侦听器");
    }
    
    @Test
    public void testAnnationResponse() {  
    	this.applicationContext = new AnnotationConfigApplicationContext(ContextConfigJMS.class);
    	ProducerService producerService = this.applicationContext.getBean(ProducerService.class, "producerService");
    	Destination responseQueue = this.applicationContext.getBean("responseQueue", Destination.class);
    	
    	producerService.sendMessage(responseQueue, "使用JMS注解的侦听器");
    	
    	try {
    		Thread.sleep(2000);
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void testJCA() {  
    	this.applicationContext = TestUtils.instanceContext(MODULE, CONFIG_JCA);
    	ProducerService producerService = this.applicationContext.getBean(ProducerService.class, "producerService");
    	Destination jcaQueue = this.applicationContext.getBean("jcaQueue", Destination.class);
    	
    	producerService.sendMessage(jcaQueue, "JMS-JCA");
    }
    
}
