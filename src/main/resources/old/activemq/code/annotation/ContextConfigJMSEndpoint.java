package com.ljt.study.jms.annotation;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.stereotype.Service;

import com.ljt.study.jms.listener.ConsumerMessageListener;

@Configuration
@Import(JMSBase.class)
@ComponentScan(basePackages="com.ljt.jms", useDefaultFilters=false,
	includeFilters=@Filter(Service.class),
	excludeFilters=@Filter(type=FilterType.REGEX, pattern="com.ljt.jms.annotation.MessageListenerService") // pattern=".*.annotation.*"
)
@EnableJms
public class ContextConfigJMSEndpoint implements JmsListenerConfigurer {
	
	@Resource(name="connectionFactory")
	private ConnectionFactory connectionFactory;

    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
        SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
        endpoint.setId("LiJingTang-JmsEndpoint");
        endpoint.setDestination("destination.queue.endpoint");
        endpoint.setMessageListener(new ConsumerMessageListener());
        
        registrar.registerEndpoint(endpoint);
    }
    
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory jmsContainerFactory = new DefaultJmsListenerContainerFactory();
        jmsContainerFactory.setConnectionFactory(connectionFactory);
        jmsContainerFactory.setConcurrency("3-10");
        
        return jmsContainerFactory;
    }
    
    @Bean
    public Destination endpointQueue() {
    	return new ActiveMQQueue("destination.queue.endpoint");
    }
    
}