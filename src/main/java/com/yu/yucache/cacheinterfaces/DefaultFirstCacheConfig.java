package com.yu.yucache.cacheinterfaces;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultFirstCacheConfig extends FirstCacheConfig{
    public DefaultFirstCacheConfig() {
        log.info("一级缓存默认配置被加载");
    }
}
