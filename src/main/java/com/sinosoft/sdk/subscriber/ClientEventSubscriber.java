package com.sinosoft.sdk.subscriber;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sinosoft.sdk.event.Event;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.StringJoiner;

/**
 * @author yanyuxin
 * 2021/8/30 10:01
 */
@Data
public class ClientEventSubscriber {

    private String eventClassName;
    @JsonIgnore
    private Method listenerMethod;
    @JsonIgnore
    private Event event;
    @JsonIgnore
    private Object listenerObject;

    @Override
    public String toString() {
        return new StringJoiner(", ", ClientEventSubscriber.class.getSimpleName() + "[", "]")
                .add("eventClassName='" + eventClassName + "'")
                .add("listenerMethod=" + listenerMethod)
                .add("event=" + event)
                .toString();
    }

    public void post() throws InvocationTargetException, IllegalAccessException {
        listenerMethod.invoke(listenerObject, event);
    }
}
