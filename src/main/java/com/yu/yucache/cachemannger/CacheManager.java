package com.yu.yucache.cachemannger;


import com.yu.yucache.base.BaseConfig;
import com.yu.yucache.cacheinterfaces.CacheConfigInterfaces;
import com.yu.yucache.factory.CacheConfigFactory;
import com.yu.yucache.instance.BaseConfigInstance;
import com.yu.yucache.properties.CacheConfigProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;

@Configuration
@Slf4j
public class CacheManager {

    public CacheManager(){
        log.info("CacheManager正在被实例化");
    }
    @Resource
    private CacheConfigFactory cacheConfigFactory;


    @Resource
    private ApplicationContext applicationContext;


    public BaseConfigInstance initConfigInstance() throws InstantiationException, IllegalAccessException {
        BaseConfigInstance baseConfigInstance = new BaseConfigInstance();
        BaseConfigInstance configInstance=null;
        List<CacheConfigInterfaces> cacheConfigInterfaces = cacheConfigFactory.CacheConfigInterfaces(applicationContext);
        for (com.yu.yucache.cacheinterfaces.CacheConfigInterfaces cacheConfigInterface : cacheConfigInterfaces) {
            BaseConfig baseConfig = cacheConfigInterface.initConfig();
            CacheConfigProperties cacheConfigProperties = baseConfig.getCacheConfigProperties();
            configInstance = baseConfigInstance.getConfigInstance(cacheConfigProperties);
        }
        return configInstance;

    }


}
