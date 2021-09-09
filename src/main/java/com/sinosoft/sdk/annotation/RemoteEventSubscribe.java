package com.sinosoft.sdk.annotation;

import java.lang.annotation.*;

/**
 * @author yanyuxin
 * 2021/8/27 17:51
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RemoteEventSubscribe {

}
