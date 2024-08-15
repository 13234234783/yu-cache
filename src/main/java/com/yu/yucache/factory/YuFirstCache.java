package com.yu.yucache.factory;


import lombok.Data;

@Data
public class YuFirstCache {

    private Long timeout=-1L;


    private YuCacheFirstFactory yuCacheFirstFactory;

    public YuFirstCache(){

    }

}
