package com.hand.cache.common.impl;

import com.hand.cache.common.RedisCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Created by zhangpengfei on 2018/9/14 11:16.
 */
@Service
public class RedisCacheServiceImpl implements RedisCacheService{

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String get(String key) {
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();
                byte [] value = redisConnection.get(redisSerializer.serialize(key));
                return redisSerializer.deserialize(value);
            }
        });
    }

    @Override
    public Boolean set(String key, String value) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> redisSerializer = redisTemplate.getStringSerializer();
                return redisConnection.set(redisSerializer.serialize(key), redisSerializer.serialize(value));
            }
        });
    }

    @Override
    public Boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }
}
