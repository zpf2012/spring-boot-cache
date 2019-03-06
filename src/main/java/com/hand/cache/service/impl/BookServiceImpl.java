package com.hand.cache.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.hand.cache.common.RedisCacheService;
import com.hand.cache.dto.Book;
import com.hand.cache.mapper.BookMapper;
import com.hand.cache.service.BookService;
import com.hand.cache.utils.TransactionManagementUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author Created by zhangpengfei on 2018/9/14 11:44.
 */
@Service
public class BookServiceImpl implements BookService {

    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private static final String LIBRARY = "library";
    private static final String MONGODB_COLLECTION = "library";


    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private RedisCacheService redisCacheService;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private TransactionManagementUtils transactionManagementUtils;

    @Override
    public List<Book> getBooks() {

        // 开启事务
        TransactionStatus status = transactionManagementUtils.begin();

        try {
            String redisData = redisCacheService.get(LIBRARY);
            if(redisData != null){
                log.info("get data from cache... ...");

                List<Book> bookList = JSON.parseArray(redisData, Book.class);

                Book result = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("bookId").is(1)), Book.class, MONGODB_COLLECTION);

                log.info("mongo data : {}", JSON.toJSONString(result));
                if(ObjectUtils.isEmpty(result)){
                    mongoTemplate.insert(bookList.get(0), MONGODB_COLLECTION);
                }
                return bookList;
            }

            List<Book> bookList = bookMapper.getBooks();
            redisCacheService.set(LIBRARY, JSON.toJSONString(bookMapper.getBooks()));
            mongoTemplate.insert(bookList);
            log.info("get data from mysql... ...");
        }catch (Exception e){
            // 回滚事务
            transactionManagementUtils.rollback(status);
        }finally {
            // 提交事务
            transactionManagementUtils.commit(status);
        }

        return Lists.newArrayList();
    }
}
