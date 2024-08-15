### yu-cache--缓存框架

#### 1.开启功能 （默认一级缓存开启，二级缓存关闭）

```java
<!--导入坐标-->
    、  <dependency>
            <groupId>com.yu</groupId>
            <artifactId>yu-cache</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

// 开启
@EnableYuCache
```

#### 2.用户自定义缓存

 2.1 一级缓存(Caffeine 为例) --二级缓存同理 （实现SecondCacheManager）

```java
@Configuration
public class MyConfig {

    // 自定义配置 
    @Bean
    public YuCacheFactory yuCacheFactory(YuDefaultsFirstConfigProperties properties){
        properties.setMaxSize(50L);
        properties.setTimeout(300*1000L);
        return new YuCacheFactory(properties);
    }
}
```

2.2 想要自定义配置 参考

```java
@Configuration
public class MyProperties extends YuDefaultsFirstConfigProperties {
    
    private int test;
}



@Configuration
public class MyConfig {
    // 自定义配置 
    @Bean
    public YuCacheFactory yuCacheFactory(MyProperties properties){
        properties.setMaxSize(50L);
        properties.setTimeout(300*1000L);
        properties.setTest(50);
        return new YuCacheFactory(properties);
    }
}
```

2.3 想要自定义本地缓存 请参考（以Caffeine缓存为例）

```java
@Configuration
public class CaffeineConfig implements YuCacheFirstFactory {

    @Bean
    public Cache<String, Object> cache(){
        return Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build();
    }

    @Override
    public Object getData(String s) {
        return cache().getIfPresent(s.toString());
    }

    @Override
    public Boolean saveData(String s, Object o) {
        cache().put(s,o);
        return true;
    }

    @Override
    public Boolean upDateData(String s, Object o) {
        cache().put(s,o);
        return true;
    }

    @Override
    public Boolean deleteData(String s) {
        return null;
    }

    @Override
    public Boolean clearData() {
        return null;
    }
}



@Configuration
public class MyConfig {
    // 自定义配置 
    @Bean
    public YuCacheFactory yuCacheFactory(CaffeineConfig caffeineConfig){
        return new YuCacheFactory(caffeineConfig);
    }
}
```

2.4 二级缓存同理

#### 3.更新缓存策略（支持自定义）

#    待更新

