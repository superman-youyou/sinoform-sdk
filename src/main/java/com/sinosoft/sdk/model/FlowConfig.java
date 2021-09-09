package com.sinosoft.sdk.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

@Data
public class FlowConfig {

    private String id;
    private String todoUrl;

    private String flowName;
    private List<SelectedDept> deptScope;

    private List<FlowNode> nodes;
    private List<FlowLine> lines;

    private String flowConfigId;
    private JSONObject fieldsAuth;

}
