package com.yu.yucache.controller;


import com.yu.yucache.annotate.YuCache;
import com.yu.yucache.annotate.YuUpDataCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TestController {


    @GetMapping("/cahcetest")
    @YuCache(keyColumn = "name")
    public String myMethod() {
        System.out.println("开始执行了");
        return "Hello, World!";
    }


}
