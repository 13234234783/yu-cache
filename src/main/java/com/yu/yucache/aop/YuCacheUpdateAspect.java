package com.yu.yucache.aop;

import com.yu.yucache.annotate.YuCache;
import com.yu.yucache.annotate.YuUpDataCache;
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
public class YuCacheUpdateAspect {


    @Resource
    private FirstCacheManager firstCacheManager;




    @Pointcut("@annotation(com.yu.yucache.annotate.YuUpDataCache)")
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
        if (method.isAnnotationPresent(YuUpDataCache.class)) {

            return null;
        } else {
            return null;
        }
    }



    @AfterReturning(pointcut = "getCacheData()", returning = "result")
    public Object afterReturningAdvice(JoinPoint joinPoint, Object result) throws NoSuchMethodException, InstantiationException, IllegalAccessException {
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        Class withinType = sourceLocation.getWithinType();
        String name = joinPoint.getSignature().getName();
        Method method = withinType.getDeclaredMethod(name);
        if (method.isAnnotationPresent(YuUpDataCache.class)) {
           return null;
        }

        return result;
    }

}
