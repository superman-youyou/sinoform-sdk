package com.sinosoft.sdk.model;

import lombok.Data;

@Data
public class FlowIdea {

    public static final String IDEA_DISABLED = "disabled";
    public static final String IDEA_REQUIRED = "required";
    public static final String IDEA_OPTIONAL = "optional";

    public static final Integer TYPE_TEMP = 0;

    public static final Integer TYPE_OFFICIAL = 1;

    private String id;
    private User user;

    private String formValueId;

    private String workItemId;
    private String wfleveName;
    private String wfleveId;

    private Integer type;

    private Integer businessType = 1;

    private String idea;
    private String signImgId;
    private String signImgUrl;
    private String content;
    private String createTime;
}
