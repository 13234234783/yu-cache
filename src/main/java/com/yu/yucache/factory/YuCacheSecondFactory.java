package com.yu.yucache.factory;

/**
 * 抽象工厂类
 */
public interface YuCacheSecondFactory {

    public Object getData(String key);

    public Boolean saveData(String key,Object val);

    public Boolean upDateData(String key,Object val);

    public Boolean deleteData(String key);

    public Boolean clearData();


}
