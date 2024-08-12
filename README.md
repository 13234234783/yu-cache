### yu-cache--缓存框架

#### 1.开启功能

```java
  @EnableYuCache(openFirstCache = true,openSecondCache = false)
```

#### 2.用户自定义缓存

 2.1 一级缓存(Caffeine 为例) --二级缓存同理 

```java
@Configuration
public class CaffeineManager implements FirstCacheManager {


    @Resource
    private Cache<String, Object> cache;   //平常正常定义即可
    

    @Override
    public Object getDataFromFirstCache(String s) {
        return cache.getIfPresent(s);
    }

    @Override
    public Boolean setDataFromFirstCache(String s, Object o) {
        try {
            cache.put(s,o);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
```

#### 3.使用(如果没有，请将查询结果返回，下次查询就走缓存了)

```java
    @GetMapping("/test")
    @YuCache(keyColumn = "name")
    public String test(){
        System.out.println("方法1走了");
        return "ok1";
    }
```

