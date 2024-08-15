package com.yu.yucache.defaults;

import com.yu.yucache.factory.YuCacheFirstFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;


@Component
public class YuDefaultFirstCache implements YuCacheFirstFactory {


    private YuDefaultsFirstConfigProperties yuDefaultsFirstConfigProperties;

    private final Timer timer = new Timer();

    public YuDefaultFirstCache() {

    }

    public YuDefaultFirstCache(YuDefaultsFirstConfigProperties yuDefaultsFirstConfigProperties) {
        this.yuDefaultsFirstConfigProperties = yuDefaultsFirstConfigProperties;
    }


    @Override
    public Object getData(String key) {
        Object res=null;
        Map<String, Map<Object, Long>> data = YuDefaultCache.getData();
        Map<Object, Long> map = data.get(key);
        if (map == null) {
            return null;
        }
        if (map.size() != 1) {
            throw new RuntimeException("未知错误，导致一个key多个value");
        }
        Set<Object> objects = map.keySet();
        for (Object object : objects) {
            res=object;
        }
        return res;
    }

    @Override
    public synchronized Boolean saveData(String key, Object val) {
        try {
            int size = YuDefaultCache.getData().size();
            Long maxSize = yuDefaultsFirstConfigProperties.getMaxSize();
            if (size >= maxSize) {
                return false;
            }
            Map<Object, Long> res = new ConcurrentHashMap<>();
            res.put(val, yuDefaultsFirstConfigProperties.getTimeout());
            YuDefaultCache.getData().put(key, res);
            if (yuDefaultsFirstConfigProperties.getTimeout() != -1) {
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        YuDefaultCache.getData().remove(key);
                    }
                }, yuDefaultsFirstConfigProperties.getTimeout());
            } else {
                timer.cancel();
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return true;
    }

    @Override
    public synchronized Boolean upDateData(String key, Object val) {
        try {
            Map<Object, Long> res = new ConcurrentHashMap<>();
            res.put(val, yuDefaultsFirstConfigProperties.getTimeout());
            YuDefaultCache.getData().put(key, res);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public synchronized Boolean deleteData(String key) {
        try {
            YuDefaultCache.getData().remove(key);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public synchronized Boolean clearData() {
        try {
            YuDefaultCache.getData().clear();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
