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

    @Transactional(propagation = Propagation.NESTED)
    public void txSpread() {
        Log inner = new Log();
        inner.setId(10);
        inner.setContent("inner");
        logMapper.insert(inner);

        throw new RuntimeException("测试事务传播");
    }

}
