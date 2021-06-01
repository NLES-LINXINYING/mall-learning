package com.example.mall.service;

/**
 * @author linxinying
 * @version 1.0.0
 * @className RedisService.java
 * @description redis操作service，对象和数组都以json形式进行存储
 * @createTime 2021-06-01 14:40:00
 */
public interface RedisService {

    /**
     * 存储数据
     */
    void set(String key, String value);

    /**
     * 获取数据
     */
    String get(String key);

    /**
     * 设置超时时间
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     */
    void remove(String key);

    /**
     * 自增操作
     * @param delta 自增步长
     */
    Long increment(String key, long delta);
}
