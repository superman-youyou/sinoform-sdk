package com.sinosoft.sdk.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.List;

@Data
public class FormConfig {
    private List<FormItem> items;
    private FormAttributes form;


}
