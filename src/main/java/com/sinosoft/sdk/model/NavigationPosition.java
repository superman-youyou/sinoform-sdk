package com.sinosoft.sdk.model;

import lombok.Data;

@Data
public class NavigationPosition {

    private String code;

    private String reference;
    private String position;
    private String resourceId;
    private String ext;

    private String superId;
}
