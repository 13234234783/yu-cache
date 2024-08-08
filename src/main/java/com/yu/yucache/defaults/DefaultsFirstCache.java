package com.yu.yucache.defaults;

import com.yu.yucache.cachemannger.FirstCacheManager;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultsFirstCache implements FirstCacheManager {
    private static final Logger log = LoggerFactory.getLogger(DefaultsFirstCache.class);
    private static BaseYuCacheFirstConfig baseYuCacheFirstConfig = new BaseYuCacheFirstConfig();
    private static HashMap<String, Object> data = new HashMap();

    public DefaultsFirstCache() {
        log.info("默认一级缓存被加载了");
    }

    public Object getDataFromFirstCache(String colName) {
        Object res = data.get(colName);
        return res;
    }

    public Boolean setDataFromFirstCache(String colName, Object value) {
        Integer size = baseYuCacheFirstConfig.getSize();
        Integer cacheSize = data.size();
        if (size < cacheSize) {
            return false;
        } else {
            data.put(colName, value);
            return true;
        }
    }
}