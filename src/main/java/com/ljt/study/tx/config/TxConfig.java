package com.ljt.study.tx.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * org.mybatis.spring.transaction.SpringManagedTransaction.getConnection()
 *
 * @author LiJingTang
 * @date 2021-06-23 17:54
 */
@Configuration
@MapperScan("com.ljt.study.tx.mapper")
public class TxConfig {

}
