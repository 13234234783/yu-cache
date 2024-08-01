package com.yu.yucache.config;

import com.yu.yucache.base.BaseConfig;
import com.yu.yucache.cacheinterfaces.CacheConfigInterfaces;
import com.yu.yucache.properties.CacheConfigProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultCacheConfig implements CacheConfigInterfaces {
    @Override
    public BaseConfig initConfig() {
        BaseConfig baseConfig = new BaseConfig();
        CacheConfigProperties build = CacheConfigProperties.builder()
                .isOpenSecondCache(true)
                .isOpenFirstCache(true)
                .firstCacheType("defa")
                .secondCacheType("redis")
                .build();
        baseConfig.setCacheConfigProperties(build);
        return baseConfig;
    }
}
