package com.hand.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author Created by zhangpengfei on 2018/12/12 14:40.
 */
@RestController
@RequestMapping("/api/cache")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("getOrderByScore")
    public ResponseEntity getOrderByScore(){
        String key = "redis";
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();

        zSetOperations.removeRange(key, 0, -1);
        double max = 0;
        for (int i = 0; i < 10000; i++){
            Random random = new Random();
            int curr = random.nextInt(10000);
            if (curr > max){
                max = curr;
            }
            zSetOperations.add(key, String.valueOf(curr), (double) curr);
        }
        return ResponseEntity.ok(zSetOperations.range(key, 0, -1));
    }

    @GetMapping("zadd")
    public ResponseEntity zadd(@RequestParam Integer score){
        String key = "zadd:test";
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();

        zSetOperations.add(key, "hello", score);

        return ResponseEntity.ok(zSetOperations.rank(key, "hello"));

    }
}
