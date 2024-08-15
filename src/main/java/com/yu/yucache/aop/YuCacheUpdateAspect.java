package com.yu.yucache.aop;

import com.yu.yucache.annotate.YuCache;
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



    @Pointcut("@annotation(com.yu.yucache.annotate.YuCache)")
    public void getCacheData() {
    }

    /**
     * @param joinPoint
     * @return
     * @throws Throwable
     */



}
