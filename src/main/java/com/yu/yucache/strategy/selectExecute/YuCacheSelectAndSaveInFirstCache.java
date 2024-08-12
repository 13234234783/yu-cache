package com.yu.yucache.strategy.selectExecute;

import com.yu.yucache.cachemannger.FirstCacheManager;
import com.yu.yucache.strategy.YuCacheSaveStrategy;

import javax.annotation.Resource;

public class YuCacheSelectAndSaveInFirstCache implements YuCacheSaveStrategy {

    @Resource
    private FirstCacheManager firstCacheManager;

    @Override
    public Boolean saveCache(String keyColName, Object val) {
        return firstCacheManager.setDataFromFirstCache(keyColName,val);
    }
}
