package com.yu.yucache.factory;

import org.springframework.stereotype.Component;

/**
 * 抽象工厂类
 */
public interface YuCacheFirstFactory {

    public Object getData(String key);

    public Boolean saveData(String key,Object val);

    public Boolean upDateData(String key,Object val);

    public Boolean deleteData(String key);

    public Boolean clearData();


}
