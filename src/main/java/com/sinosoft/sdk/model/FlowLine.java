package com.sinosoft.sdk.model;

import lombok.Data;

import java.util.List;

@Data
public class FlowLine {

    private static final String RELATETYPE_CROSS = "cross";
    private static final String RELATETYPE_SAME = "save";
    private static final String RELATETYPE_DIRECT = "direct";

    private String id;
    private String title;
    private String sourceRef;
    private String sourceEdge;
    private String targetRef;
    private String targetEdge;
    private Double[][] path;
    private String relateRange;
    private String relateType;

    private String controlExpression;
    private List<ControlCondition> controlCondition;

    private List<String> participantCondition;

    private String isNeedBack;

    private String isBeginSign;

    private String isEndSign;

}
