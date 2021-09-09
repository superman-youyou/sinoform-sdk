package com.sinosoft.sdk.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FormDesign implements Serializable {

    private String id;
    private String title;
    private Boolean publishStatus;
    private FormConfig formConfig;
    private List<FlowConfig> flowConfigs;
    private List<User> viewers;
    private NavigationPosition navigationPosition;
    private String createUserId;
    private String createUserName;
    private String createDeptId;
    private String createDeptName;
    private String createTime;
    private String updateTime;
    private String sysId;
    private String tableName;
    private String applicationId;
    private Integer formType = 1;
    private FormPermission permission;
    private String loginDeptId;
    private FlowConfig currentFlowConfig;
}
