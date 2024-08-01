package com.yu.yucache.cacheinterfaces;

import com.yu.yucache.base.BaseConfig;

/**
 * 工厂接口的入口
 */
public interface CacheConfigInterfaces {

    /**
     * 返回值未这个CacheManager
     * @return
     */
    BaseConfig initConfig();
}
