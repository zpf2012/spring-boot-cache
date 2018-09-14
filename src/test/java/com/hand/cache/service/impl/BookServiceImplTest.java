package com.hand.cache.service.impl;

import com.alibaba.fastjson.JSON;
import com.hand.cache.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

/**
 * @author Created by zhangpengfei on 2018/9/14 12:04.
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    public void getBooks() {
        int count = 100;

        for (int i = 0; i < count; i++) {
            System.out.println(JSON.toJSON(bookService.getBooks()));
        }

    }
}