package com.hand.cache.controller;

import com.hand.cache.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by zhangpengfei on 2018/9/14 11:46.
 */
@Api(value = "书籍管理", description = "书籍管理")
@RestController
@RequestMapping("/api/library/")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "查询所有的书籍")
    @GetMapping("/getBooks")
    public ResponseEntity getBooks(){

        return ResponseEntity.ok(bookService.getBooks());
    }
}
