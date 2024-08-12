package com.yu.yucache.factory;


import com.yu.yucache.annotate.EnableYuCache;
import com.yu.yucache.cachemannger.FirstCacheManager;
import com.yu.yucache.cachemannger.SecondCacheManager;
import com.yu.yucache.defaults.DefaultsFirstCache;
import com.yu.yucache.defaults.InvalidityFirstCache;
import com.yu.yucache.defaults.InvaliditySecondCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 配置工厂
 */
@Component
@Slf4j
public class YuCacheFactory {
    @Resource
    private ApplicationContext applicationContext;


    public YuCacheFactory() {
        log.info("YuCacheFactory被加载");
    }


    public Map<String, Boolean> getCacheState() throws InstantiationException, IllegalAccessException {

        HashMap<String, Boolean> map = new HashMap<>();
        log.info("同时加载配置");
        Object object = null;
        Map<String, Object> enAble = this.applicationContext.getBeansWithAnnotation(EnableYuCache.class);
        if (enAble.isEmpty()) {
            throw new RuntimeException("请在启动类添加启动注解EnableYuCache");
        }
        if (enAble.size() != 1) {
            throw new RuntimeException("EnableYuCache注解唯一");
        }

        Object beanWithAnnotation = enAble.values().iterator().next();
        Class<?> beanClass = beanWithAnnotation.getClass();
        EnableYuCache enableYuCache = AnnotationUtils.findAnnotation(beanClass, EnableYuCache.class);
        if (enableYuCache != null) {
            // 处理注解的属性
            map.put("first", enableYuCache.openFirstCache());
            map.put("second", enableYuCache.openSecondCache());
            return map;
        }

        return null;

    }

    @Bean
    public FirstCacheManager firstCacheManager() throws InstantiationException, IllegalAccessException {
        if (this.getCacheState() != null) {
            if (!this.getCacheState().get("first")) {
                return new InvalidityFirstCache();
            }
        }
        Map<String, FirstCacheManager> map = applicationContext.getBeansOfType(FirstCacheManager.class);
        Set<String> keySet = map.keySet();
        if (keySet.size() > 3) {
            throw new RuntimeException("配置过多的FirstCacheManager，请删除无用的FirstCacheManager");
        }
        for (String key : keySet) {
            if (!key.equals("DefaultsFirstCache")) {
                return map.get(key);
            }
        }
        return new DefaultsFirstCache();
    }


    @Bean
    public SecondCacheManager secondCacheManager() throws InstantiationException, IllegalAccessException {
        if (this.getCacheState() != null) {
            if (!this.getCacheState().get("second")) {
                return new InvaliditySecondCache();
            }
        }
        Map<String, SecondCacheManager> map = applicationContext.getBeansOfType(SecondCacheManager.class);
        Set<String> keySet = map.keySet();
        if (keySet.size() > 2) {
            throw new RuntimeException("配置过多的SecondCacheManager，请删除无用的SecondCacheManager");
        }
        for (String key : keySet) {
            if (!key.equals("InvaliditySecondCache")) {
                return map.get(key);
            }
        }
        return new InvaliditySecondCache();
    }

}
