package com.ljt.study.jms.annotation;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@Configuration
@Import(JMSBase.class)
@ComponentScan(basePackages="com.ljt.jms")
@EnableJms
public class ContextConfigJMS {
	
	@Resource(name="connectionFactory")
	private ConnectionFactory connectionFactory;

    @Bean(name="jmsContainerFactory")
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory jmsContainerFactory = new DefaultJmsListenerContainerFactory();
        jmsContainerFactory.setConnectionFactory(connectionFactory);
        jmsContainerFactory.setConcurrency("3-10");
        
        return jmsContainerFactory;
    }
    
    @Bean
    public Destination annotationQueue() {
    	return new ActiveMQQueue("destination.queue.annotation");
    }
    
    @Bean
    public Destination responseQueue() {
    	return new ActiveMQQueue("destination.queue.annotation.response");
    }

}