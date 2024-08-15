package com.yu.yucache.defaults;

import com.yu.yucache.factory.YuCacheFirstFactory;
import com.yu.yucache.factory.YuCacheSecondFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class YuDefaultSecondCache implements YuCacheSecondFactory {

    public YuDefaultSecondCache() {

    }


    @Override
    public Object getData(String key) {
       return null;
    }

    @Override
    public synchronized Boolean saveData(String key, Object val) {
        return false;

    }

    @Override
    public synchronized Boolean upDateData(String key, Object val) {
        return false;
    }

    @Override
    public synchronized Boolean deleteData(String key) {
        return false;
    }

    @Override
    public synchronized Boolean clearData() {
        return false;
    }
}
