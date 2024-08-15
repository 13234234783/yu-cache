package com.yu.yucache.factory;

import com.yu.yucache.annotate.EnableYuCache;
import com.yu.yucache.defaults.YuDefaultFirstCache;
import com.yu.yucache.defaults.YuDefaultSecondCache;
import com.yu.yucache.defaults.YuDefaultsFirstConfigProperties;
import lombok.Data;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.util.Map;


@Data
public class YuCacheFactory<T> {


    @Resource
    private ApplicationContext applicationContext;

    private YuCacheFirstFactory yuCacheFirstFactory = new YuDefaultFirstCache();

    public YuCacheSecondFactory yuCacheSecondFactory = new YuDefaultSecondCache();

    private YuDefaultsFirstConfigProperties properties = new YuDefaultsFirstConfigProperties();

    private T bean = null;

    public YuCacheFactory() {

    }


    /**
     * HashMap<String, Boolean> map = new HashMap<>();
     * log.info("同时加载配置");
     * Object object = null;
     * Map<String, Object> enAble = this.applicationContext.getBeansWithAnnotation(EnableYuCache.class);
     * if (enAble.isEmpty()) {
     * throw new RuntimeException("请在启动类添加启动注解EnableYuCache");
     * }
     * if (enAble.size() != 1) {
     * throw new RuntimeException("EnableYuCache注解唯一");
     * }
     *
     * @param yuCacheFirstFactory
     */

    public YuCacheFactory(YuCacheFirstFactory yuCacheFirstFactory) {
        Map<String, Object> enAble = this.applicationContext.getBeansWithAnnotation(EnableYuCache.class);
        if (enAble.isEmpty()) {
            throw new RuntimeException("请在启动类添加启动注解EnableYuCache");
        }
        if (enAble.size() != 1) {
            throw new RuntimeException("EnableYuCache注解唯一");
        }



        this.yuCacheFirstFactory = yuCacheFirstFactory;
        this.properties = null;
    }

    public YuCacheFactory(YuCacheFirstFactory yuCacheFirstFactory,
                          YuCacheSecondFactory yuCacheSecondFactory,
                          YuDefaultsFirstConfigProperties properties) {
        this.yuCacheFirstFactory = new YuDefaultFirstCache(properties);
        this.yuCacheSecondFactory = yuCacheSecondFactory;
        this.properties = properties;
    }

    public YuCacheFactory(YuDefaultsFirstConfigProperties properties) {
        this.yuCacheFirstFactory = new YuDefaultFirstCache(properties);
        this.properties = properties;
    }


    public YuCacheFactory(YuCacheSecondFactory yuCacheSecondFactory) {
        this.yuCacheSecondFactory = yuCacheSecondFactory;
    }


}
