package com.yu.yucache.defaults;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;



@Component
public class YuDefaultCache {

    private static Map<String,Map<Object,Long>> data=null;

    private YuDefaultCache(){
        if(data==null){
            data=new ConcurrentHashMap<>();
        }
    }

    public static Map<String,Map<Object,Long>> getData(){
        return YuDefaultCache.data;
    }
}
