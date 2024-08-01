package com.yu.yucache.controller;


import com.yu.yucache.annotate.EnableYuCache;
import com.yu.yucache.annotate.YuCache;
import com.yu.yucache.annotate.YuUpDataCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping
public class TestController {




    @GetMapping("/test")
    @YuCache(keyColumn = "name")
    public String myMethod() {
        System.out.println("开始执行了");
        return "Hello, World!";
    }


    @GetMapping("/test1")
    @YuCache(keyColumn = "age")
    public String myMethod1() {
        return "Hello, World!";
    }


    @GetMapping("/test2")
    @YuUpDataCache
    @YuCache(keyColumn = "age")
    public String myMethod2() {
        return "Hello, World!";
    }
}
