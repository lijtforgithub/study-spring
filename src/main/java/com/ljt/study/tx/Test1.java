package com.ljt.study.tx;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author LiJingTang
 * @date 2023-03-17 14:21
 */
@Data
public class Test1 {

    private Integer id;
    private String content;
    private Date createTime;
    private LocalDate date;
    private LocalTime time;

}
