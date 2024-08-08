package com.yu.yucache.annotate;


import com.yu.yucache.YuCacheApplication;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({

})
public @interface EnableYuCache {

    boolean openFirstCache() default false;

    boolean openSecondCache() default true;

}
