package com.ljt.study.tx.controller;

import com.ljt.study.model.Log;
import com.ljt.study.tx.service.TxLogService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author LiJingTang
 * @date 2021-06-23 20:50
 */
@RestController
@RequestMapping("/tx")
public class TxController {

    @Autowired
    private TxLogService logService;

    @SneakyThrows
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @GetMapping("/logs")
    public String findAllLog() {
        logService.findAll().forEach(System.out::println);
        TimeUnit.SECONDS.sleep(30);
        logService.findAll().forEach(System.out::println);
        return "OK";
    }

    @GetMapping("/log")
    public Long findLog() {
        logService.save(new Log().setId(1000).setContent("NO TX"));
        return cost();
    }

    /**
     * 同一个事务 所有SQL使用一个数据库连接
     * MySQL 只读不可以执行非查询操作
     * java.sql.Connection#setReadOnly(boolean)
     */
    @GetMapping("/log-tx")
    @Transactional(readOnly = true)
    public Long findLogTx() {
        // Connection is read-only. Queries leading to data modification are not allowed
        logService.save(new Log().setId(1000).setContent("@Transactional(readOnly = true)"));
        return cost();
    }

    private Long cost() {
        long start = System.currentTimeMillis();
        IntStream.rangeClosed(1, 10).forEach(i -> logService.getById(i));
        long cost = System.currentTimeMillis() - start;
        System.out.println(cost);
        return cost;
    }

}
