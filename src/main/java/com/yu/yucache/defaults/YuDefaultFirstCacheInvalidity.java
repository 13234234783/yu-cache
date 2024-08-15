package com.yu.yucache.defaults;

import com.yu.yucache.factory.YuCacheFirstFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;


public class YuDefaultFirstCacheInvalidity implements YuCacheFirstFactory {


    @Override
    public Object getData(String key) {
        return null;
    }

    @Override
    public Boolean saveData(String key, Object val) {
        return null;
    }

    @Override
    public Boolean upDateData(String key, Object val) {
        return null;
    }

    @Override
    public Boolean deleteData(String key) {
        return null;
    }

    @Override
    public Boolean clearData() {
        return null;
    }
}
