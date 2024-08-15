package com.yu.yucache.factory;

import com.yu.yucache.defaults.YuDefaultFirstCache;
import com.yu.yucache.defaults.YuDefaultSecondCache;
import com.yu.yucache.defaults.YuDefaultsFirstConfigProperties;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Data
public class YuCacheFactory<T> {


    private YuCacheFirstFactory yuCacheFirstFactory=new YuDefaultFirstCache();

    public YuCacheSecondFactory yuCacheSecondFactory=new YuDefaultSecondCache();

    private YuDefaultsFirstConfigProperties properties=new YuDefaultsFirstConfigProperties();

    private T bean=null;

    public YuCacheFactory(){

    }


    public YuCacheFactory(YuCacheFirstFactory yuCacheFirstFactory){
        this.yuCacheFirstFactory=yuCacheFirstFactory;
        this.properties=null;
    }
    public YuCacheFactory(YuCacheFirstFactory yuCacheFirstFactory,
                          YuCacheSecondFactory yuCacheSecondFactory,
                          YuDefaultsFirstConfigProperties properties){
        this.yuCacheFirstFactory = new YuDefaultFirstCache(properties);
        this.yuCacheSecondFactory = yuCacheSecondFactory;
        this.properties = properties;
    }

    public YuCacheFactory(YuDefaultsFirstConfigProperties properties){
        this.yuCacheFirstFactory = new YuDefaultFirstCache(properties);
        this.properties = properties;
    }


    public YuCacheFactory(YuCacheSecondFactory yuCacheSecondFactory){
        this.yuCacheSecondFactory = yuCacheSecondFactory;
    }





}
