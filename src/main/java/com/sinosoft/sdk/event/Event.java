package com.sinosoft.sdk.event;

import java.io.Serializable;

/**
 * @author yanyuxin
 * 2021/8/19 11:13
 */
public interface Event extends Serializable {
    public String getEventClassName();
}
