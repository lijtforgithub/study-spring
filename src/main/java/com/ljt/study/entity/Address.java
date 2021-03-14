package com.ljt.study.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LiJingTang
 * @date 2021-03-14 11:26
 */
@Data
public class Address implements Serializable {

    private static final long serialVersionUID = 5516426967032115914L;

    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String region;

}
