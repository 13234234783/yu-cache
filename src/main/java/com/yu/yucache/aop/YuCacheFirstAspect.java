package com.yu.yucache.aop;

import com.yu.yucache.annotate.YuCache;
import com.yu.yucache.cachemannger.FirstCacheManager;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

@Aspect
@Component
@EnableAspectJAutoProxy
@Slf4j
public class YuCacheFirstAspect {


    @Resource
    private FirstCacheManager firstCacheManager;


    @Pointcut("@annotation(com.yu.yucache.annotate.YuCache)")
    public void getCacheData() {
    }


/*
    @Before("getCacheData()")
    public void beforeGetCacheData(JoinPoint joinPoint) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // 提前加载好配置信息
        BaseConfigInstance baseConfigInstance = cacheManager.initConfigInstance();
        CacheConfigProperties cacheConfigProperties = baseConfigInstance.getCacheConfigProperties();
        // 拿到cachemanner  根据管理器看看里面配置 看看一级缓存开不开等
        //1. 开启了一级缓存
        // 1.1 拿数据   拿到返回结果
        //2. 没拿到数据
        // 2.1 是否开启了二级缓存
        //  2.2 开启了 拿数据   拿到返回
        //  2.3 没拿到  返回空 等结果赋值
        // 拿到一级缓存
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        Class withinType = sourceLocation.getWithinType();
        String name = joinPoint.getSignature().getName();
        Method method = withinType.getDeclaredMethod(name);
        Object o;
        if(method.isAnnotationPresent(YuCache.class)){
            YuCache annotation = method.getAnnotation(YuCache.class);
            String keyColumn = annotation.keyColumn();
            // 如果一级缓存开启
            if(cacheConfigProperties.getIsOpenFirstCache()){
                // 在一级缓存里面拿到值
                o = cache.getIfPresent(keyColumn);
                if(o==null){  // 一级缓存没有
                    if(cacheConfigProperties.getIsOpenSecondCache()){ // 二级缓存开启
                        // 从二级缓存查
                    }
                    else {
                        //直接查数据库
//                        return joinPoint.proceed();
                    }

                }
            }

            return o;
            // 缓存存在命中了 不走二级缓存 直接返回结果
        }else {
            // 说明该方法上没有注解 有异常
            return null;
        }

        System.out.println(joinPoint.getClass());

    }*/

    /**
     * @param joinPoint
     * @return
     * @throws Throwable
     */

    @Around("getCacheData()")
    public Object AroundCacheData(ProceedingJoinPoint joinPoint) throws Throwable {
        Object resCacheData = null;
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        Class withinType = sourceLocation.getWithinType();
        String name = joinPoint.getSignature().getName();
        Method method = withinType.getDeclaredMethod(name);
        if (method.isAnnotationPresent(YuCache.class)) {
            YuCache annotation = (YuCache) method.getAnnotation(YuCache.class);
            String keyColumn = annotation.keyColumn();
            resCacheData = firstCacheManager.getDataFromFirstCache(keyColumn);
            if (resCacheData == null) {
                log.info("目标值一级缓存没有---暂不请求二级缓存直接请求后台");
                resCacheData = joinPoint.proceed();
            }

            return resCacheData;
        } else {
            return null;
        }
    }



/*    @Around("getCacheData()")
    public Object AroundCacheData(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("注解进入了");
        // 拿到管理器类 看看管理器的值
        // 拿到目标类
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        Class withinType = sourceLocation.getWithinType();
        String name = joinPoint.getSignature().getName();
        System.out.println("注解进入了"+"aaaa"+name);
        Method method = withinType.getDeclaredMethod(name);
        if(method.isAnnotationPresent(YuCache.class)){
            YuCache annotation = method.getAnnotation(YuCache.class);
            String keyColumn = annotation.keyColumn();
            // 在一级缓存里面拿到值
            Object o = cache.getIfPresent(keyColumn);
            if(o==null){
                Object proceed = joinPoint.proceed();
                o=proceed;
            }
            return o;
            // 缓存存在命中了 不走二级缓存 直接返回结果
        }else {
            // 说明该方法上没有注解 有异常
            return null;
        }

    }*/


    @AfterReturning(pointcut = "getCacheData()", returning = "result")
    public Object afterReturningAdvice(JoinPoint joinPoint, Object result) throws NoSuchMethodException {
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        Class withinType = sourceLocation.getWithinType();
        String name = joinPoint.getSignature().getName();
        Method method = withinType.getDeclaredMethod(name);
        if (method.isAnnotationPresent(YuCache.class)) {
            YuCache annotation = (YuCache) method.getAnnotation(YuCache.class);
            String keyColumn = annotation.keyColumn();
            firstCacheManager.setDataFromFirstCache(keyColumn, result);
            log.info("已从后台拿到返回值{}", result);
        }

        return result;
    }

}
