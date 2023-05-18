package com.ljt.study.ldap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ldap.core.LdapTemplate;

/**
 * @author LiJingTang
 * @date 2023-05-18 15:54
 */
@SpringBootTest
class LdapBootTest {

    @Autowired
    private LdapTemplate ldapTemplate;

    @Test
    void test() {

    }

}
