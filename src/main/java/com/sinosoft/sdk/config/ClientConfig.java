package com.sinosoft.sdk.config;

import com.sinosoft.sdk.client.SinoFormEventClient;
import com.sinosoft.sdk.processor.PostProcessor;
import com.sinosoft.sdk.util.EventUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.util.StringUtils;

/**
 * @author yanyuxin
 * 2021/8/27 14:56
 */
@ComponentScan({"com.sinosoft"})
@Configuration
@Import(PostProcessor.class)
@Slf4j
public class ClientConfig {

    @Value("${sinoform.event.server.ip:}")
    private String remoteEventServerIP;

    @Value("${sinoform.event.server.port:}")
    private String remoteEventServerPort;

    @Value("${sinoform.event.server.registerURL:/sinoform/server/apis/registerEventListener}")
    private String remoteEventServerRegisterURL;

    @Value("${sinoform.event.client.ip:}")
    private String remoteEventClientIP;

    @Value("${sinoform.event.client.port:}")
    private String remoteEventClientPort;

    @Value("${sinoform.event.client.postURL:/sinoform/client/apis/post}")
    private String remoteEventClientPostURL;

    @Value("${sinoform.event.client.group:client}")
    private String group;

    @Value("${server.port:}")
    private String serverPort;

    @Bean
    public SinoFormEventClient sinoFormEventClient() {
        SinoFormEventClient client = new SinoFormEventClient();
        try {
            client.setServerAddressList(EventUtil.getAddress(remoteEventServerIP, remoteEventServerPort, remoteEventServerRegisterURL));
        } catch (Exception e) {
            log.error("Get sinoform.event.server config failed. " ,e);
        }
        client.setPostIP(remoteEventClientIP);
        if(StringUtils.isEmpty(remoteEventClientPort)) {
            client.setPostPort(serverPort);
        } else {
            client.setPostPort(remoteEventClientPort);
        }
        client.setPostURL(remoteEventClientPostURL);
        client.setGroup(group);
        return client;
    }
}
