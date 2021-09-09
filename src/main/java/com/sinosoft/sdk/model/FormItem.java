package com.sinosoft.sdk.model;

import lombok.Data;

import java.util.List;

@Data
public class FormItem {

    private String id;
    private String fieldName;
    private String title;
    private String type;
    private String width;

    private List<FormItem> children;
    private String tableName;
    private boolean layoutFormField = false;
    private Integer layerIndex;
}
