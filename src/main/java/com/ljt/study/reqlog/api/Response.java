package com.ljt.study.reqlog.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author jtli3
 * @date 2022-04-15 10:17
 */
@Data
@AllArgsConstructor
public class Response<T> implements Serializable {

    protected String code = "200";
    protected String msg = "success";
    protected T data;

}