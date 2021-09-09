package com.sinosoft.sdk.util;

import com.alibaba.fastjson.JSONObject;
import com.sinosoft.sdk.annotation.RemoteEventSubscribe;
import com.sinosoft.sdk.client.SinoFormEventClient;
import com.sinosoft.sdk.event.Event;
import com.sinosoft.sdk.exception.ConfigAddressException;
import com.sinosoft.sdk.subscriber.ClientEventSubscriber;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author yanyuxin
 * 2021/8/19 11:07
 */
public class EventUtil {

    public static final String SERVER_INFO_SEPARATOR = ",";

    public static List<ClientEventSubscriber> methodToEventListener(List<Method> methodList, Object listenerBean) {
        List<ClientEventSubscriber> eventListeners = new ArrayList<>();
        methodList.forEach(method -> eventListeners.add(methodToEventListener(method, listenerBean)));
        return eventListeners;
    }

    public static ClientEventSubscriber methodToEventListener(Method method, Object listenerBean) {
        ClientEventSubscriber clientEventListener = new ClientEventSubscriber();
        clientEventListener.setListenerMethod(method);
        clientEventListener.setEventClassName(method.getParameterTypes()[0].getName());
        clientEventListener.setListenerObject(listenerBean);
        return clientEventListener;
    }

    public static List<String> getAddress(String ipAddr, SinoFormEventClient client) throws Exception {
        String ip = ipAddr;
        if (client.getPostIP() != null && !"".equals(client.getPostIP())) {
            ip = client.getPostIP();
        }

        return getAddress(ip, client.getPostPort(), client.getPostURL());
    }

    public static List<String> getAddress(String ip, String port, String url) throws Exception {
        if (StringUtils.isEmpty(ip) || StringUtils.isEmpty(port)) {
            return null;
        }

        List<String> result = new ArrayList<>();

        String[] ips = ip.split(SERVER_INFO_SEPARATOR);
        String[] ports = port.split(SERVER_INFO_SEPARATOR);
        String[] urls = url.split(SERVER_INFO_SEPARATOR);

        //检查参数配置的正确性
        if(!checkServerInfoConfig(ips.length, ports.length, urls.length)) {
            throw new ConfigAddressException();
        }
        //获取最大参数个数
        int maxLength = getMaxNumber(ips.length, ports.length, urls.length);
        //组装请求地址
        for (int i = 0; i < maxLength; i++) {
            result.add("http://" + getStringByIndex(ips, i) + ":" + getStringByIndex(ports, i) + getStringByIndex(urls, i));
        }
        return result;
    }

    public static String getStringByIndex(String [] strs, int index) {
        if (strs.length < (index + 1)) {
            return strs[0];
        } else {
            return strs[index];
        }
    }

    public static int getMaxNumber(Integer ... numbers) {
        if (numbers == null || numbers.length == 0) {
            return 0;
        }
        List<Integer> numberList = Arrays.asList(numbers);
        Collections.sort(numberList);

        return numberList.get(numberList.size() - 1);
    }

    /**
     * 检查服务端地址配置的参数个数，检查规则：
     *     参数个数大于1个的，所有参数个数应该相等，否则没法组合url地址
     * @param lengths
     * @return
     */
    public static boolean checkServerInfoConfig(int ... lengths) {
        if (lengths == null || lengths.length == 0) {
            return false;
        }

        int maxLength = 0;
        for (int i = 0; i < lengths.length; i++) {
            if (lengths[i] > 1) {
                if (maxLength == 0) {
                    maxLength = lengths[i];
                } else {
                    if (maxLength != lengths[i]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    public static Event jsonObjectToEvent (JSONObject eventJSONObject) throws ClassNotFoundException {
        String eventClassName = eventJSONObject.getString("eventClassName");
        Class<?> aClass = EventUtil.class.getClassLoader().loadClass(eventClassName);
        return (Event) JSONObject.parseObject(eventJSONObject.toJSONString(), aClass);
    }

    public static List<ClientEventSubscriber> scanEventListener(Object listenerBean) {
        List<Method> methodList = ClassScanUtil.scanMethod(ClassUtils.getUserClass(listenerBean), RemoteEventSubscribe.class, Event.class);
        return methodToEventListener(methodList, listenerBean);
    }

}
