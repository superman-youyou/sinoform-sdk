package com.sinosoft.sdk.event;

import com.alibaba.fastjson.JSONObject;
import com.sinosoft.sdk.model.FlowIdea;
import com.sinosoft.sdk.model.FormDesign;
import com.sinosoft.sdk.model.FormValue;

/**
 * @author yanyuxin
 * 2021/8/19 11:18
 */
public class FlowEndEvent extends WorkFlowEvent {

    public FlowEndEvent() {
        super();
    }

    public FlowEndEvent(FormValue formValue, FormDesign formDesign, JSONObject workFlowParam, JSONObject workFlowResult, FlowIdea workFlowIdea, String userId, String userName) {
        super(formValue, formDesign, workFlowParam, workFlowResult, workFlowIdea, userId, userName);
    }
}
