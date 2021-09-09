package com.sinosoft.sdk;

import com.sinosoft.sdk.client.SinoFormEventClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SinoFormClientStarter implements CommandLineRunner {

    @Autowired
    private SinoFormEventClient eventClient;

    public void startEventClient() {
        log.info("智能表单服务地址 : " + eventClient.getServerAddressList());
        log.info("当前项目的事件发布URL : " + eventClient.getPostURL());
        log.info("订阅事件数量 : " + eventClient.getEventListeners().size());
        log.info("订阅事件列表 : ");
        eventClient.getEventListeners().forEach(clientEventListener -> {
            log.info("        " + clientEventListener.getListenerObject().getClass().getName() + "." + clientEventListener.getListenerMethod().getName() + "(" + clientEventListener.getEventClassName() + ")");
        });
        log.info("开始订阅 : ");
        eventClient.register();
    }

    @Override
    public void run(String... args) throws Exception {
        startEventClient();
    }
}
