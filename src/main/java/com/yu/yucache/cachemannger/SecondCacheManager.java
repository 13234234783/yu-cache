package com.yu.yucache.cachemannger;

public interface SecondCacheManager {
    Object getDataFromSecondCache(String colName);

    Boolean setDataFromSecondCache(String colName, Object val);
}
