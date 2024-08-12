package com.yu.yucache.cachemannger;

import com.yu.yucache.defaults.BaseYuCacheFirstConfig;

public interface FirstCacheManager {


    BaseYuCacheFirstConfig baseYuCacheFirstConfig(BaseYuCacheFirstConfig baseYuCacheFirstConfig);
    Object getDataFromFirstCache(String colName);

    Boolean setDataFromFirstCache(String colName, Object val);
}
