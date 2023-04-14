package com.ljt.study.tx;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ljt.study.entity.Log;
import com.ljt.study.tx.mapper.Test1Mapper;
import com.ljt.study.tx.service.TxLogService;
import com.ljt.study.tx.spread.OuterTxService;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

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

    @Autowired
    private Test1Mapper test1Mapper;

    @Test
    void genData() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("D:\\Workspace\\优码\\定时器（集成xxl_job）使用文档.md");

        byte[] bytes = IOUtils.toByteArray(fileInputStream);

        Test1 t = new Test1();
        t.setId(1);
        t.setContent("xxxx");
        t.setCreateTime(new Date());
        t.setDate(LocalDate.now());
        t.setTime(LocalTime.now());
//        test1Mapper.insert(t);
//        IntStream.rangeClosed(1, 10).forEach(i -> {
//            Log log = new Log().setId(i).setContent("spring 声明式事务：" + i);
//            logService.save(log);
//        });

        LambdaUpdateWrapper<Test1> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Test1::getContent, "");
        test1Mapper.update(t, wrapper);
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

}
