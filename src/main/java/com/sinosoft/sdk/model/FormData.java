package com.sinosoft.sdk.model;

import lombok.Data;

import java.util.List;

@Data
public class FormData {
    private String id;
    private String deptId;
    private String status;
    private String userId;
    private List<String> deptIds;
    private List<String> roleIds;
    private List<String> userIds;
    private List<String> deptInfo;
    private String deptName;
    private String userName;
    private String createTime;
    private String workflowid;
    private List<String> deptInfoName;
    private String workflowStatus;
}
