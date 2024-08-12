package com.yu.yucache.strategy.selectcontext;

import com.yu.yucache.factory.YuCacheFactory;
import com.yu.yucache.strategy.YuCacheSaveStrategy;
import com.yu.yucache.strategy.selectExecute.YuCacheSelectAndSaveInSecondCache;
import com.yu.yucache.strategy.selectExecute.YuCacheSelectAndSaveInFirstCache;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class YuCacheContext {

    public YuCacheSaveStrategy specificExecute(YuCacheFactory yuCacheFactory) throws InstantiationException, IllegalAccessException {
        Map<String, Boolean> cacheState = yuCacheFactory.getCacheState();
        if(cacheState.get("first") && cacheState.get("second")){
            // 一二级缓存均开启
            return new YuCacheSelectAndSaveInSecondCache();
        }
        if(cacheState.get("first") && !cacheState.get("second")){
            return new YuCacheSelectAndSaveInFirstCache(yuCacheFactory);
        }
        if(!cacheState.get("first") && cacheState.get("second")){
            return new YuCacheSelectAndSaveInSecondCache();
        }
        return null;
    }







}
