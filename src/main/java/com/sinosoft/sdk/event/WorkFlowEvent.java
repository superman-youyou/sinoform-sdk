package com.sinosoft.sdk.event;

import com.alibaba.fastjson.JSONObject;
import com.sinosoft.sdk.model.FlowIdea;
import com.sinosoft.sdk.model.FormDesign;
import com.sinosoft.sdk.model.FormValue;
import lombok.Data;

/**
 * @author yanyuxin
 * 2021/8/19 11:16
 */
@Data
public class WorkFlowEvent implements Event{
    /**
     * 事件class名称
     */
    private String eventClassName;
    /**
     * 表单数据
     */
    private FormValue formValue;
    /**
     * 表单配置信息
     */
    private FormDesign formDesign;
    /**
     * 流程参数
     */
    private JSONObject workFlowParam;
    /**
     * 流程结果
     */
    private JSONObject workFlowResult;
    /**
     * 流程意见
     */
    private FlowIdea workFlowIdea;
    /**
     *
     */
    private String userId;
    /**
     *
     */
    private String userName;

    public WorkFlowEvent() {
        setEventClassName();
    }

    public void setEventClassName() {
        this.eventClassName = this.getClass().getName();
    }

    public WorkFlowEvent(FormValue formValue, FormDesign formDesign, JSONObject workFlowParam, JSONObject workFlowResult, FlowIdea workFlowIdea, String userId, String userName) {
        this.formValue = formValue;
        this.formDesign = formDesign;
        this.workFlowParam = workFlowParam;
        this.workFlowResult = workFlowResult;
        this.workFlowIdea = workFlowIdea;
        this.userId = userId;
        this.userName = userName;
        setEventClassName();
    }
}
