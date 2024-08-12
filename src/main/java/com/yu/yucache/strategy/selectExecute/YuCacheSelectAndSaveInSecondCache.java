package com.yu.yucache.strategy.selectExecute;

import com.yu.yucache.cachemannger.SecondCacheManager;
import com.yu.yucache.strategy.YuCacheSaveStrategy;

import javax.annotation.Resource;

public class YuCacheSaveInSecondCache implements YuCacheSaveStrategy {

    @Resource
    private SecondCacheManager secondCacheManager;

    @Override
    public Boolean saveCache(String keyColName, Object val) {
        return secondCacheManager.setDataFromSecondCache(keyColName,val);
    }
}
