package com.sinosoft.sdk.processor;

import com.sinosoft.sdk.client.SinoFormEventClient;
import com.sinosoft.sdk.subscriber.ClientEventSubscriber;
import com.sinosoft.sdk.util.EventUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;

public class PostProcessor implements BeanPostProcessor {

    private List<ClientEventSubscriber> listenerList = new ArrayList<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean,
            String beanName) throws BeansException {
        if (bean instanceof SinoFormEventClient) {
            SinoFormEventClient client = (SinoFormEventClient) bean;
            client.setEventListeners(listenerList);
            return client;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean,
            String beanName) throws BeansException {
        listenerList.addAll(EventUtil.scanEventListener(bean));
        return bean;
    }

}