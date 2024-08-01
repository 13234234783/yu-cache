package com.yu.yucache.config;

import com.yu.yucache.aop.YuCacheFirstAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfig {

    @Bean
    public YuCacheFirstAspect cacheFirstAspect(){
        return new YuCacheFirstAspect();
    }

}
