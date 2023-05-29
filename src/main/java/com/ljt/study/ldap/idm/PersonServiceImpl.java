package com.ljt.study.ldap.idm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.naming.directory.Attributes;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;
import static org.springframework.ldap.query.SearchScope.SUBTREE;

/**
 * @author LiJingTang
 * @date 2023-05-22 19:44
 */
@Service
public class PersonServiceImpl implements PersonService {

    private final String BASE_DN = "ou=People,o=huafa.com,o=isp";

    @Autowired
    private LdapTemplate ldapTemplate;

    public List<Person> getAllPersons() {
        LdapQuery query = query().base(BASE_DN).countLimit(10).searchScope(SUBTREE).where("smart-type").is("E1");
        return ldapTemplate.search(query, new PersonAttributesMapper());
    }

    @Override
    public Person getPerson(String username) {
        LdapQuery query = query().base(BASE_DN).countLimit(1).searchScope(SUBTREE).where("smart-alias").is(username);
        List<Person> list = ldapTemplate.search(query, new PersonAttributesMapper());
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }


    private static class PersonAttributesMapper implements AttributesMapper<Person> {

        @Override
        public Person mapFromAttributes(Attributes attrs) {
            return DnAttributeHelper.mapFromAttributes(attrs, Person.class);
        }
    }

}
