package com.yu.yucache.annotate;


import com.yu.yucache.cachemannger.CacheManager;
import com.yu.yucache.config.CaffeineConfig;
import com.yu.yucache.config.DefaultCacheConfig;
import com.yu.yucache.config.InitConfig;
import com.yu.yucache.factory.CacheConfigFactory;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({InitConfig.class,
        CaffeineConfig.class,
        CacheManager.class,
        CacheConfigFactory.class,
        DefaultCacheConfig.class})
public @interface EnableYuCache {

    boolean openFirstCache() default false;

    boolean openSecondCache() default true;

}
