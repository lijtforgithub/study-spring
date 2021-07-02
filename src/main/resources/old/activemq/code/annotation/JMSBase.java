package com.ljt.study.jms.annotation;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@ImportResource("classpath:/jms/mq-config.xml")
@EnableJms
public class JMSBase {
	
	@Value("${mq.brokerURL}")
    private String brokerURL;
    @Value("${mq.userName}")
    private String userName;
    @Value("${mq.password}")
    private String password;
    @Value("${mq.maxConnections}")
    private int maxConnections;

	@Bean
	@Scope("singleton")
	public ConnectionFactory targetConnectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(brokerURL);
		connectionFactory.setUserName(userName);
		connectionFactory.setPassword(password);
		
		return connectionFactory;
	}
	
	@Bean
	public PooledConnectionFactory pooledConnectionFactory() {
		PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
		pooledConnectionFactory.setMaxConnections(maxConnections);
		pooledConnectionFactory.setConnectionFactory(targetConnectionFactory());
		
		return pooledConnectionFactory;
	}
	
	@Bean
	public ConnectionFactory connectionFactory() {
		SingleConnectionFactory connectionFactory = new SingleConnectionFactory();
		connectionFactory.setTargetConnectionFactory(targetConnectionFactory());
		
		return connectionFactory;
	}
	
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(connectionFactory());
		
		return jmsTemplate;
	}
	
}