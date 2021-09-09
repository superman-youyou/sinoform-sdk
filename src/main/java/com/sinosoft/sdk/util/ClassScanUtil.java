package com.sinosoft.sdk.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClassScanUtil {
    public static List<Method> scanMethod(Class clazz, Class<? extends Annotation> filterAnnotation, Class filterParamType) {
        return scanMethod(clazz, new ArrayList<Class<? extends Annotation>>(){{add(filterAnnotation);}}, new ArrayList<Class>(){{add(filterParamType);}});
    }

    public static List<Method> scanMethod(Class clazz, List<Class<? extends Annotation>> filterAnnotationList, List<Class> filterParamTypeList) {
        List<Method> methodList = filterMethodByAnnotation(clazz, filterAnnotationList);
        methodList = filterMethodByParameterType(methodList, filterParamTypeList);
        return methodList;
    }

    public static List<Method> filterMethodByAnnotation (Class clazz, List<Class<? extends Annotation>> filterAnnotationList) {
        Method[] methods = clazz.getMethods();
        if (methods == null) {
            return null;
        }

        List<Method> methodList = Arrays.asList(methods);
        //无需过滤注解
        if (filterAnnotationList == null){
            return methodList;
        }

        return methodList.stream()
                .filter(method -> filterAnnotationList.stream()
                        .filter(annotation -> method.getAnnotation(annotation) != null).findAny().isPresent())
                .collect(Collectors.toList());
    }

    public static List<Method> filterMethodByParameterType(List<Method> methodList, List<Class> filterParamTypeList) {
        //无需过滤
        if (filterParamTypeList == null) {
            return methodList;
        }

        List<Method> resultList = new ArrayList<>();
        for (Method method : methodList) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            //无参过滤
            if (filterParamTypeList.size() == 0) {
                //method无参匹配
                if (parameterTypes == null || parameterTypes.length == 0) {
                    resultList.add(method);
                }
                continue;
            }
            //有参过滤
            //参数个数不一致continue
            if(parameterTypes.length != filterParamTypeList.size()) {
                continue;
            }

            boolean isMatch = true;
            //匹配类型
            for (int i = 0; i < filterParamTypeList.size(); i++) {
                if (!filterParamTypeList.get(i).isAssignableFrom(parameterTypes[i])) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                resultList.add(method);
            }
        }

        return resultList;
    }
}
