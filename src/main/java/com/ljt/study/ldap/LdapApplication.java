package com.ljt.study.ldap;

import com.ljt.study.YamlPropertySourceFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author LiJingTang
 * @date 2023-05-18 15:51
 */
@SpringBootApplication
@PropertySource(value = "classpath:ldap/application.yml", factory = YamlPropertySourceFactory.class)
class LdapApplication {
}
