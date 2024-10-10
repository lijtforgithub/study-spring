package com.ljt.study.tx;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljt.study.entity.Log;
import com.ljt.study.tx.service.TxLogService;
import com.ljt.study.tx.spread.OuterTxService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.stream.IntStream;

/**
 * @author LiJingTang
 * @date 2021-06-23 17:59
 */
@SpringBootTest
class TxTest {

    @Resource
    private TxLogService logService;
    @Resource
    private OuterTxService outerTxService;


    @Test
    void genData() throws IOException {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Log log = new Log().setId(i).setContent("" + i);
            logService.save(log);
        });
    }

    @Test
    void testSelect() {
        Page<Log> page = logService.findPage();
        System.out.println(JSON.toJSONString(page));
//        IntStream.rangeClosed(1, 10).forEach(i -> logService.getById(i));
    }

    @Test
    void txSpread() {
        outerTxService.txSpread();
    }

    @Test
    void txInvoke() {
        outerTxService.txInvokeA();
    }

}
