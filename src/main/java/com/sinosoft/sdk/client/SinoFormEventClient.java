package com.sinosoft.sdk.client;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sinosoft.sdk.subscriber.ClientEventSubscriber;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yanyuxin
 * 2021/8/27 11:37
 */
@Data
@Slf4j
public class SinoFormEventClient implements Serializable {

    @JsonIgnore
    private List<String> serverAddressList;

    private String postIP;

    private String postPort;

    private String postURL;

    private String group;

    private List<ClientEventSubscriber> eventListeners = new ArrayList<>();

    public void register() {
        if(eventListeners == null) {
            log.info("        没有找到需要订阅的事件.");
            return;
        }
        if (serverAddressList == null) {
            log.info("        没有找到智能表单服务的地址信息.");
            return;
        }

        String requestJson = JSONObject.toJSONString(this);

        log.info("        订阅请求：" + requestJson);
        for (String serverAddress : serverAddressList) {
            try {
                String post = HttpUtil.post(serverAddress, requestJson);
                if ("ok".equals(post)) {
                    log.info("        向智能表单服务 {} 订阅成功. {}", serverAddress, post);
                    break;
                }else{
                    log.info("        向智能表单服务 {} 订阅失败. 错误信息：{}", serverAddress, post);
                }
            } catch (Exception e) {
                log.info("        向智能表单服务 {} 订阅失败. 错误信息：{}", serverAddress, e.getMessage());
            }
        }
    }

    public void postOne(List<String> urlList, Object event) {
        String jsonString = JSONObject.toJSONString(event);
        for (String url : urlList) {
            try {
                HttpUtil.post(url, jsonString);
                log.info("发布事件成功 postURL=" + url + " event=" + jsonString);
                break;
            } catch (Exception e) {
                log.error("发布事件失败 postURL=" + url + " event=" + jsonString);
            }
        }
    }

    public void post(String url, Object event) {
        String jsonString = JSONObject.toJSONString(event);
        try {

            HttpUtil.post(url, jsonString);
            log.info("发布事件成功 postURL=" + url + " event=" + jsonString);
        } catch (Exception e) {
            log.error("发布事件失败 postURL=" + url + " event=" + jsonString);
        }


    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
