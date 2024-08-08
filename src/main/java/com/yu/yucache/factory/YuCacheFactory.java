package com.yu.yucache.factory;


import com.yu.yucache.cachemannger.FirstCacheManager;
import com.yu.yucache.defaults.DefaultsFirstCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * 配置工厂
 */
@Configuration
@Slf4j
public class YuCacheFactory {
    @Resource
    private ApplicationContext applicationContext;

    public YuCacheFactory() {
        log.info("YuCacheFactory被加载");
    }


    @Bean
    public FirstCacheManager firstCacheManager() throws InstantiationException, IllegalAccessException {
        Map<String, FirstCacheManager> map = applicationContext.getBeansOfType(FirstCacheManager.class);
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            if (!key.equals("DefaultsFirstCache")) {
                return FirstCacheManager.class.newInstance();
            }
        }
        return new DefaultsFirstCache();
    }

}
