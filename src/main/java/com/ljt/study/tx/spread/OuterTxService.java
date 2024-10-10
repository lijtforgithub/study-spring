package com.ljt.study.tx.spread;

import com.ljt.study.entity.Log;
import com.ljt.study.tx.mapper.TxLogMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author LiJingTang
 * @date 2022-07-07 15:49
 */
@Service
public class OuterTxService {

    @Resource
    private TxLogMapper logMapper;
    @Resource
    private InnerTxService innerTxService;

    @Transactional
    public void txSpread() {
        try {

            String content =  RandomStringUtils.randomAlphabetic(5);
            Log outer = new Log();
            outer.setId(3);
            outer.setContent(content);
            logMapper.updateById(outer);

            innerTxService.txSpread1(content);
            innerTxService.txSpread2(content);
//            throw new RuntimeException("测试事务传播");
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void txInvokeA() {
        System.out.println(this.getClass());
        this.txInvokeB();
    }

    @Transactional
    public void txInvokeB() {
        String content = RandomStringUtils.randomAlphabetic(5);
        Log outer = new Log();
        outer.setId(4);
        outer.setContent(content);
        logMapper.updateById(outer);
        throw new RuntimeException("测试事务");
    }

}
