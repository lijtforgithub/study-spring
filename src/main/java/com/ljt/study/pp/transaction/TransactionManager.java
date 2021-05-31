package com.ljt.study.pp.transaction;

import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author LiJingTang
 * @date 2021-05-27 15:52
 */
public class TransactionManager {

    public void execute(Callable callable) {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
                @Override
                public void afterCommit() {
                    callable.call();
                }
            });
        } else {
            callable.call();
        }
    }

    public interface Callable {
        /**
         * 回调方法
         */
        void call();
    }

}
