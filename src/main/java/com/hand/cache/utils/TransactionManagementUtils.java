package com.hand.cache.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @author Created by zhangpengfei on 2019-03-06 12:03.
 *
 * desc: 声明式事务
 */
@Component
public class TransactionManagementUtils {

    @Autowired
    private DataSourceTransactionManager transactionManager;

    public TransactionStatus begin(){
        return transactionManager.getTransaction(new DefaultTransactionDefinition());
    }

    public void commit(TransactionStatus status){
        transactionManager.commit(status);
    }

    public void rollback(TransactionStatus status){
        transactionManager.rollback(status);
    }
}
