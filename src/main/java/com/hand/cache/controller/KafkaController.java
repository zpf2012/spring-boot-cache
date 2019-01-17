package com.hand.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by zhangpengfei on 2019/1/17 15:43.
 */
@RequestMapping("/api/cache/kafka")
@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @PutMapping("/sendMessage")
    public ResponseEntity sendMessage(@RequestParam String name){

        kafkaTemplate.send("hello", name);

        return ResponseEntity.ok().build();
    }
}
