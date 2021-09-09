package com.sinosoft.sdk.model;

import lombok.Data;

@Data
public class NodeOper {

    private String id;
    private String name;
    private Boolean enabled;
    private Boolean calculated;

    private Boolean hidden;
}
