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
            YuCache annotation = method.getAnnotation(YuCache.class);
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



    @AfterReturning(pointcut = "getCacheData()", returning = "result")
    public Object afterReturningAdvice(JoinPoint joinPoint, Object result) throws NoSuchMethodException {
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        Class withinType = sourceLocation.getWithinType();
        String name = joinPoint.getSignature().getName();
        Method method = withinType.getDeclaredMethod(name);
        if (method.isAnnotationPresent(YuCache.class)) {
            YuCache annotation = method.getAnnotation(YuCache.class);
            String keyColumn = annotation.keyColumn();
            firstCacheManager.setDataFromFirstCache(keyColumn, result);
            log.info("已从后台拿到返回值{}", result);
        }

        return result;
    }

}
