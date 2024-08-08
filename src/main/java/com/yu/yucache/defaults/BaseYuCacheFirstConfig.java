package com.yu.yucache.defaults;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BaseYuCacheFirstConfig {
    private Integer size = 50;
}