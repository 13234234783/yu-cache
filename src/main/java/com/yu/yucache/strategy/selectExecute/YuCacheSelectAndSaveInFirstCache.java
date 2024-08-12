package com.yu.yucache.strategy.selectExecute;

import com.yu.yucache.cachemannger.FirstCacheManager;
import com.yu.yucache.cachemannger.SecondCacheManager;
import com.yu.yucache.factory.YuCacheFactory;
import com.yu.yucache.strategy.YuCacheSaveStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class YuCacheSelectAndSaveInFirstCache implements YuCacheSaveStrategy {


    private FirstCacheManager firstCacheManager;


    public YuCacheSelectAndSaveInFirstCache(YuCacheFactory yuCacheFactory) throws InstantiationException, IllegalAccessException {
        this.firstCacheManager=yuCacheFactory.firstCacheManager();
    }



    @Override
    public Boolean saveCache(String keyColName, Object val) {
        return firstCacheManager.setDataFromFirstCache(keyColName,val);
    }
}
