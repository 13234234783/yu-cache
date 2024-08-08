package com.yu.yucache;

import com.yu.yucache.annotate.EnableYuCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableYuCache
public class YuCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuCacheApplication.class, args);
    }

}
