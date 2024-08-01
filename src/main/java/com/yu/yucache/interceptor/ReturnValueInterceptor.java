package com.yu.yucache.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ReturnValueInterceptor implements InvocationHandler {
    private final Object target;

    public ReturnValueInterceptor(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(target, args);
        System.out.println("Method " + method.getName() + " returned: " + result);
        return result;
    }

    public static Object newProxyInstance(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new ReturnValueInterceptor(target));
    }
}