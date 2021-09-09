package com.sinosoft.sdk.controller;

import com.alibaba.fastjson.JSONObject;
import com.sinosoft.sdk.apis.SinoFormClientApis;
import com.sinosoft.sdk.client.SinoFormEventClient;
import com.sinosoft.sdk.event.Event;
import com.sinosoft.sdk.util.EventUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;

/**
 * @author yanyuxin
 * 2021/8/27 11:05
 */
@RestController
@Slf4j
public class SinoFormClientController implements SinoFormClientApis {

    @Autowired
    private SinoFormEventClient sinoFormEventClient;

    @Override
    public ResponseEntity post(JSONObject eventJSONObject) throws ClassNotFoundException {
        log.info("Received event = " + eventJSONObject);
        String eventClassName = eventJSONObject.getString("eventClassName");
        Event event = EventUtil.jsonObjectToEvent(eventJSONObject);
        sinoFormEventClient.getEventListeners().stream()
                .filter(clientEventListener ->clientEventListener.getEventClassName().equals(eventClassName))
                .forEach(clientEventListener -> {
                    try {
                        clientEventListener.setEvent(event);
                        clientEventListener.post();
                        log.info("Event post success. Event name = " + eventClassName);
                    } catch (InvocationTargetException|IllegalAccessException e) {
                        log.info("Event post failed. EventName = {}  ErrorMessage = {}", eventClassName, e);
                    }
                });
        return ResponseEntity.ok("成功");
    }

}
