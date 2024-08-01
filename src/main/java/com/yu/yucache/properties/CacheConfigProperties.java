package com.yu.yucache.properties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CacheConfigProperties {

    /**
     * 是否开启一级缓存
     */
    Boolean isOpenFirstCache;
    /**
     * 是否开启二级缓存
     */
    Boolean isOpenSecondCache;
    /**
     * 一级缓存类型
     */
    Object firstCacheType;
    /**
     * 二级缓存类型
     */
    Object secondCacheType;
}
