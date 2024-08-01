package com.yu.yucache.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@EnableAspectJAutoProxy
@Component
@Slf4j
public class yuAnnotateTest {


    @Pointcut("@annotation(com.yu.yucache.annotate.YuUpDataCache)")
    public void getCacheDataTest() {
    }

    @Before("getCacheDataTest()")
    public void beforeGetCacheData(JoinPoint joinPoint){
        System.out.println("进前置校验了");
    }

}
