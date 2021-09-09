package com.sinosoft.sdk.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

@Data
public class FlowNode {
    private String id;
    private String type;
    private String name;
    private Double x;
    private Double y;
    private Candidate candidate;
    private String idea;

    private JSONObject fieldsAuth;
    private List<NodeOper> nodeOperation;
    private String doneType;
    private List<SelectedDept> selectedDepts;
    private String isInnerflow;
    private String innerflowRouteName;

    private String relateType;
}
