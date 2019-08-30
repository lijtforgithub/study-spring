package com.ljt.study.pp.redis.integrate;

import static com.ljt.study.util.Constant.REDIS_TEST_KEY_PREFIX;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import com.ljt.study.AbstractTest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author LiJingTang
 * @date 2019年8月27日 上午11:32:45
 */
@Slf4j
public class IntegrateTest extends AbstractTest {
    
    private static final String KEY_PRIFIX = REDIS_TEST_KEY_PREFIX + "integrate::";
    
    @Test
    public void test() {
        String key = KEY_PRIFIX + "singleton";
        applicationContext = new AnnotationConfigApplicationContext(RedisConfig.class);
        @SuppressWarnings("unchecked")
        RedisTemplate<String, String> redisTemplate = applicationContext.getBean(RedisTemplate.class);
        redisTemplate.opsForValue().set(key , "singleton");
        log.info("{} = {}", key, redisTemplate.opsForValue().get(key));
    }
    
    

}
