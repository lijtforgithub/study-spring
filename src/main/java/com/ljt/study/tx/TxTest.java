package com.ljt.study.tx;

import com.ljt.study.entity.Log;
import com.ljt.study.tx.service.TxLogService;
import com.ljt.study.tx.spread.OuterTxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

/**
 * @author LiJingTang
 * @date 2021-06-23 17:59
 */
@SpringBootTest
class TxTest {

    @Autowired
    private TxLogService logService;

    @Autowired
    private OuterTxService outerTxService;

    @Test
    void genData() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Log log = new Log().setId(i).setContent("spring 声明式事务：" + i);
            logService.save(log);
        });
    }

    @Test
    void testSelect() {
        IntStream.rangeClosed(1, 10).forEach(i -> logService.getById(i));
    }

    @Test
    void txSpread() {
        outerTxService.txSpread();
    }

}
