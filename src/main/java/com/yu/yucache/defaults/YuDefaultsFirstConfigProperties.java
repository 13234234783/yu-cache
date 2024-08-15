package com.yu.yucache.defaults;


import lombok.Data;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Data
@Component
@Order(1)
public class YuDefaultsFirstConfigProperties {

    private Long timeout=-1L;

    private Long maxSize=50L;


}
