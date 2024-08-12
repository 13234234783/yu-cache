package com.yu.yucache.defaults;

import com.yu.yucache.cachemannger.FirstCacheConfigManager;
import com.yu.yucache.cachemannger.FirstCacheManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Synchronized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class DefaultsFirstCache implements FirstCacheManager {
    private static final Logger log = LoggerFactory.getLogger(DefaultsFirstCache.class);
    private  BaseYuCacheFirstConfig baseYuCacheFirstConfig = null;

    @Resource
    private FirstCacheConfigManager firstCacheConfigManager;
    private final Map<String, Object> data = new ConcurrentHashMap<>();

    private final Timer timer=new Timer();

    public DefaultsFirstCache() {
        log.info("默认一级缓存被加载了");
        this.baseYuCacheFirstConfig=new BaseYuCacheFirstConfig();
    }


    public DefaultsFirstCache(BaseYuCacheFirstConfig baseYuCacheFirstConfig) {
        log.info("默认一级缓存被加载了====同时加载用户自定义一级缓存配置");
        this.baseYuCacheFirstConfig(baseYuCacheFirstConfig);
    }

    @Override
    public BaseYuCacheFirstConfig baseYuCacheFirstConfig(BaseYuCacheFirstConfig baseYuCacheFirstConfig) {
        this.baseYuCacheFirstConfig=new BaseYuCacheFirstConfig();
        return this.baseYuCacheFirstConfig;
    }

    @Override
    public Object getDataFromFirstCache(String colName) {
        Object res = data.get(colName);
        return res;
    }


    @Override
    public  Boolean setDataFromFirstCache(String colName, Object value) {
        synchronized(this) {
            Integer size = firstCacheConfigManager.baseYuCacheFirstConfig().getSize();
//            Integer size = baseYuCacheFirstConfig.getSize();
            Integer cacheSize = data.size();
            if (size <= cacheSize) {
                return false;
            } else {
                Long timeout = firstCacheConfigManager.baseYuCacheFirstConfig().getTimeout();
                if(timeout==-1){
                    // 永不过期---取消所有定时任务
                    data.put(colName, value);
                    timer.cancel();
                }else {
                    // 有过期时间
                    Map<Object, Long> valMap = new ConcurrentHashMap<>();
                    valMap.put(value,timeout);
                    data.put(colName,valMap);
                    scheduleRemove(colName,timeout);
                    Object o = getDataFromFirstCache(colName);
                    log.info("ssss+{}",o);
                    timer.cancel();
                }
                return true;
            }
        }
    }

    public void scheduleRemove(String key,Long timeout) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                data.remove(key);
            }
        };
        timer.schedule(task, timeout);
    }
}