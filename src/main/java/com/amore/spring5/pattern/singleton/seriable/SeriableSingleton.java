package com.amore.spring5.pattern.singleton.seriable;

import java.io.Serializable;

public class SeriableSingleton implements Serializable {

    private static SeriableSingleton instance = new SeriableSingleton();

    static {
        System.out.println("SeriableSingleton loading...");
    }

    //私有构造器
    private SeriableSingleton() {
        System.out.println("create object...");
    }

    public static SeriableSingleton getInstance() {
        return instance;
    }

    private Object readResolve() {
        //反序列化回调方法，实际还是创建了多个对象只是没有返回新创建的对象
        return instance;
    }
}
