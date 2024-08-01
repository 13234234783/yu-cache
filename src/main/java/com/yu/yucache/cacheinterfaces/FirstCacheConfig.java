package com.yu.yucache.cacheinterfaces;

/**
 * 一级缓存配置模板类
 */
public abstract class FirstCacheConfig {

    /**
     * 缓存初始容量
     */
    public Integer initialCapacity;


    /**
     * 缓存最大容量
     */
    public Long maximumSize;


    /**
     * 缓存过期时间
     */
    public Long expireAfterAccess;
}
