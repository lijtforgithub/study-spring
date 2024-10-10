package com.ljt.study.tx.spread;

import com.ljt.study.entity.Log;
import com.ljt.study.tx.mapper.TxLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author LiJingTang
 * @date 2022-07-07 15:50
 */
@Service
public class InnerTxService {

    @Autowired
    private TxLogMapper logMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void txSpread1(String content) {
        Log inner = new Log();
        inner.setId(1);
        inner.setContent(content);
        logMapper.updateById(inner);

//        throw new RuntimeException("测试事务传播1");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void txSpread2(String content) {
        Log inner = new Log();
        inner.setId(2);
        inner.setContent(content);
        logMapper.updateById(inner);

        throw new RuntimeException("测试事务传播2");
    }

}
