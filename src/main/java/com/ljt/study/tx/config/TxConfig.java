package com.ljt.study.tx.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * org.mybatis.spring.transaction.SpringManagedTransaction.getConnection()
 *
 * @author LiJingTang
 * @date 2021-06-23 17:54
 */
@Configuration
@MapperScan("com.ljt.study.tx.mapper")
public class TxConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
