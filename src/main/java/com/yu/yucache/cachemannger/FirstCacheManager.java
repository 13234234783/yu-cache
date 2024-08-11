package com.yu.yucache.cachemannger;

public interface FirstCacheManager {
    Object getDataFromFirstCache(String colName);

    Boolean setDataFromFirstCache(String colName, Object val);
}
