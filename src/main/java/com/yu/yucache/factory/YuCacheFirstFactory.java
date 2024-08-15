package com.yu.yucache.factory;

/**
 * 抽象工厂类
 */
public interface YuCacheFirstFactory {

    public Boolean saveData(String key,Object val);

    public Boolean upDateData(String key,Object val);

    public Boolean deleteData(String key,Object val);

    public Boolean clearData(String key,Object val);


}
