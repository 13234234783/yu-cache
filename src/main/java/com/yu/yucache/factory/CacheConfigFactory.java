package com.yu.yucache.factory;


import com.yu.yucache.cacheinterfaces.CacheConfigInterfaces;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;

import java.util.*;

/**
 * 配置工厂
 */
@Configuration
@Slf4j
public class CacheConfigFactory {

    public CacheConfigFactory(){
        log.info("CacheConfigFactory正在实例化");
    }


    private final List<CacheConfigInterfaces> cacheConfigInterfaces=new ArrayList<>();


    /**
     * 根据代理获取具体实现类
     * @return
     */
    @Bean
    public List<CacheConfigInterfaces> CacheConfigInterfaces(ApplicationContext applicationContext) throws InstantiationException, IllegalAccessException {


        //根据ApplicationContext获取配置接口的所有实现类
        Map<String, CacheConfigInterfaces> map = applicationContext.getBeansOfType(CacheConfigInterfaces.class);
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            CacheConfigInterfaces interfaces = map.get(key);
            cacheConfigInterfaces.add(interfaces);
        }
        log.info("List<CacheConfigInterfaces> 所有配置已被加载");
        return this.cacheConfigInterfaces;
    }
}
