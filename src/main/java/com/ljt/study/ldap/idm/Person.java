package com.ljt.study.ldap.idm;

import lombok.Data;

/**
 * @author LiJingTang
 * @date 2023-05-22 19:35
 */
@Data
public class Person {

    private String uid;

    private String employeeNumber;
    private String cn;
    private String mobile;
    private String ou;
    private String title;
    private String departmentNumber;
    private String departmentName;

    private String smart;
    private String smartStatus;
    private String smartOrder;
    private String smartGender;
    private String smartAlias;
    private String smartOrgnoFullpath;
    private String smartOrgnameFullpath;

    private String customizedCompanyid;
    private String customizedDutyid;
    private String customizedDutyname;
    private String customizedPositionid;
    private String customizedIdmparttime;

}
