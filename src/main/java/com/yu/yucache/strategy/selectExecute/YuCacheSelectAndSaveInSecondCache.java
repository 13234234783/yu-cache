package com.yu.yucache.strategy.selectExecute;

import com.yu.yucache.cachemannger.SecondCacheManager;
import com.yu.yucache.strategy.YuCacheSaveStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class YuCacheSelectAndSaveInSecondCache implements YuCacheSaveStrategy {

    @Resource
    private SecondCacheManager secondCacheManager;

    @Override
    public Boolean saveCache(String keyColName, Object val) {
        return secondCacheManager.setDataFromSecondCache(keyColName,val);
    }
}
