package com.yu.yucache.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;




public class DefaultCacheConfigProperties extends CacheConfigProperties{
    public DefaultCacheConfigProperties(Boolean isOpenFirstCache, Boolean isOpenSecondCache, Object firstCacheType, Object secondCacheType) {
        super(isOpenFirstCache, isOpenSecondCache, firstCacheType, secondCacheType);
    }

    /**
     * 是否开启一级缓存
     */
    Boolean isOpenFirstCache=false;
    /**
     * 是否开启二级缓存
     */
    Boolean isOpenSecondCache=true;
    /**
     * 一级缓存类型
     */
    Object firstCacheType;
    /**
     * 二级缓存类型
     */
    Object secondCacheType;


}
