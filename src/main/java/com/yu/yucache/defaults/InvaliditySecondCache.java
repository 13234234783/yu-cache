package com.yu.yucache.defaults;


import com.yu.yucache.cachemannger.FirstCacheManager;
import com.yu.yucache.cachemannger.SecondCacheManager;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InvaliditySecondCache implements SecondCacheManager {
    @Override
    public Object getDataFromSecondCache() {
        return null;
    }

    @Override
    public Boolean setDataFromSecondCache() {
        return null;
    }
}
