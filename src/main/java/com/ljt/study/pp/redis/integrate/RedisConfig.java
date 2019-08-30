package com.ljt.study.pp.redis.integrate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author LiJingTang
 * @date 2019年8月27日 上午11:03:02
 */
@Configuration
@PropertySource("classpath:pp/redis/config.properties")
public class RedisConfig {
    
//    @Value("${redis.maxTotal}")
//    private int maxTotal;
//    @Value("${redis.maxIdle}")
//    private int maxIdle;
//    @Value("${redis.maxTotal}")
//    private int maxWaitMillis;
//    
//    @Value("${redis.host:localhost}")
//    private String host;
//    @Value("${redis.port:6379}")
//    private int port;
    
//    @Bean
//    public JedisPoolConfig poolConfig() {
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(maxTotal);
//        config.setMaxIdle(maxIdle);
//        config.setMaxWaitMillis(maxWaitMillis);
//        return config;
//    }
    
    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        return factory;
    }
    
    @Bean
    public RedisTemplate<String, String> redisTemplate(JedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        RedisSerializer<?> serializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setValueSerializer(serializer);
        
        return redisTemplate;
    }

}
