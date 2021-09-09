package com.sinosoft.sdk.model;

import lombok.Data;

@Data
public class Permission {
    private String name;
    private Boolean hidden;
    private Boolean readOnly;

}
