package com.yu.yucache.cachemannger;

import com.yu.yucache.defaults.BaseYuCacheFirstConfig;
import org.springframework.stereotype.Component;

@Component
public interface FirstCacheConfigManager{

    BaseYuCacheFirstConfig baseYuCacheFirstConfig();

}
