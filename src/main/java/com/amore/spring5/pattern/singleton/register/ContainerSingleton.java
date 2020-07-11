package com.amore.spring5.pattern.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContainerSingleton {

    //注册式单例实现
    private static final Map<String, Object> ioc = new ConcurrentHashMap<>();

    //私有构造器
    private ContainerSingleton(){

    }

    //全局访问点
   public static Object getBean(String beanName) {
        if (beanName != null && !beanName.isEmpty()) {
            synchronized (ioc){
                Object regist = ioc.get(beanName);
                if (regist!=null) {
                    return regist;
                } else {
                    ContainerSingleton singleton = new ContainerSingleton();
                    ioc.put(beanName,singleton);
                    return singleton;
                }
            }
        }
        return null;
   }
}
