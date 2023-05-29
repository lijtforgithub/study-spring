package com.ljt.study.ldap;

import com.alibaba.fastjson.JSON;
import com.ljt.study.ldap.idm.Person;
import com.ljt.study.ldap.idm.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author LiJingTang
 * @date 2023-05-18 15:54
 */
@Slf4j
@SpringBootTest
class LdapBootTest {

    @Autowired
    private PersonService personService;

    @Test
    void testIdm() {
        Person list = personService.getPerson("ouyangjunjiang");
        log.info(JSON.toJSONString(list));
    }

}
