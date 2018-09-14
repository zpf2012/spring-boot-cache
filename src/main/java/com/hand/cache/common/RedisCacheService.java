package com.hand.cache.common;

/**
 * @author Created by zhangpengfei on 2018/9/14 11:14.
 */
public interface RedisCacheService {

    /**
     * 查询
     *
     * @param key key
     * @return value
     */
    String get(String key);

    /**
     * 插入
     *
     * @param key key
     * @param value value
     * @return 是否插入成功
     */
    Boolean set(String key, String value);

    /**
     * 设置过期时间
     *
     * @param key key
     * @param time 时间
     * @return 是否设置成功
     */
    Boolean expire(String key, long time);
}
