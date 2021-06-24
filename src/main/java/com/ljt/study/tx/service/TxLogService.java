package com.ljt.study.tx.service;

import com.ljt.study.model.Log;
import com.ljt.study.tx.mapper.TxLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiJingTang
 * @date 2021-06-23 17:57
 */
@Service
public class TxLogService {

    @Autowired
    private TxLogMapper logMapper;

    public void save(Log log) {
        logMapper.insert(log);
    }

    public Log getById(int id) {
        return logMapper.selectById(id);
    }

    public List<Log> findAll() {
        return logMapper.selectList(null);
    }

}
