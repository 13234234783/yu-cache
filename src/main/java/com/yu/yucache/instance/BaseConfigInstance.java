package com.yu.yucache.instance;

import com.yu.yucache.properties.CacheConfigProperties;
import lombok.Data;

@Data
public class BaseConfigInstance {

    private CacheConfigProperties cacheConfigProperties;

    public BaseConfigInstance() {
    }

    public BaseConfigInstance(CacheConfigProperties cacheConfigProperties) {
        this.cacheConfigProperties = cacheConfigProperties;
    }

    public BaseConfigInstance getConfigInstance(CacheConfigProperties cacheConfigProperties){
        Object firstCacheType = cacheConfigProperties.getFirstCacheType();
        Boolean isOpenFirstCache = cacheConfigProperties.getIsOpenFirstCache();
        Object secondCacheType = cacheConfigProperties.getSecondCacheType();
        Boolean isOpenSecondCache = cacheConfigProperties.getIsOpenSecondCache();
        return new BaseConfigInstance(cacheConfigProperties);
    }

}
