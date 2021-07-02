package com.ljt.study.tx.service;

import com.ljt.study.entity.Log;
import com.ljt.study.tx.mapper.TxLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiJingTang
 * @date 2021-06-23 17:57
 */
@Service
public class TxLogService {

    private static final String SELECT_ALL = "SELECT * FROM log";

    @Autowired
    private TxLogMapper logMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Log log) {
        logMapper.insert(log);
    }

    public Log getById(int id) {
        return logMapper.selectById(id);
    }

    public List<Log> findAll() {
        return logMapper.selectList(null);
    }

    public List<Log> findAllUserJdbc() {
        return jdbcTemplate.query(SELECT_ALL, new BeanPropertyRowMapper<Log>(Log.class));
    }

}
