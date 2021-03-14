package com.ljt.study.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LiJingTang
 * @date 2020-01-03 20:39
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -8368263216275207741L;

    private int id;
    private String name;
    private String password;

    private Address address;

}
