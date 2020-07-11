package com.amore.spring5.pattern.singleton.hungry;

public class HungryStaticSingleton {
    static {
        //懒汉式写法在类加载的时候就创建了实例,线程安全
        instance = new HungryStaticSingleton();
    }

    private static final HungryStaticSingleton instance;

    private HungryStaticSingleton() {
        //防止反射暴力破解
        if (instance != null) {
            throw new RuntimeException("该实例已经创建");
        }
    }

    public static HungryStaticSingleton getInstance() {
        return instance;
    }
}
