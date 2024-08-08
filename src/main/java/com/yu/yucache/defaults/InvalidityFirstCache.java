package com.yu.yucache.defaults;


import com.yu.yucache.cachemannger.FirstCacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class InvalidityFirstCache implements FirstCacheManager {
    @Override
    public Object getDataFromFirstCache(String var1) {
        return null;
    }

    @Override
    public Boolean setDataFromFirstCache(String var1, Object var2) {
        return null;
    }
}
