package com.ljt.study.tx.spread;

import com.ljt.study.entity.Log;
import com.ljt.study.tx.mapper.TxLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author LiJingTang
 * @date 2022-07-07 15:49
 */
@Service
public class OuterTxService {

    @Autowired
    private TxLogMapper logMapper;
    @Autowired
    private InnerTxService innerTxService;

    @Transactional
    public void txSpread() {
        try {

            Log outer = new Log();
            outer.setId(1);
            outer.setContent("outer");
            logMapper.insert(outer);

            innerTxService.txSpread();
//            throw new RuntimeException("测试事务传播");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

}
