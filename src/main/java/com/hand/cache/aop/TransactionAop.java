package com.hand.cache.aop;

import com.hand.cache.utils.TransactionManagementUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

/**
 * @author Created by zhangpengfei on 2019-03-06 12:31.
 *
 * desc: aop 使用 around 进行事务控制
 */
@EnableAspectJAutoProxy
@Component
public class TransactionAop {

    @Autowired
    private TransactionManagementUtils transactionManagementUtils;

    private TransactionStatus status;

    @Pointcut("execution(* com.hand.cache.service.BookService.getBooks())")
    public void pointCut(){
    }

    @AfterThrowing("pointCut")
    public void afterThrowing(){
        transactionManagementUtils.rollback(status);
    }

    @Around("pointCut")
    public void around(ProceedingJoinPoint joinPoint){
        status = transactionManagementUtils.begin();
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            transactionManagementUtils.rollback(status);
        } finally {
            transactionManagementUtils.commit(status);
        }
    }
}
