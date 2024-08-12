package com.yu.yucache.defaults;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BaseYuCacheFirstConfig {

    /**
     * 缓存大小
     */
    private Integer size = 50;

    /**
     * 过期时间
     */
    private Long timeout = -1L;
}