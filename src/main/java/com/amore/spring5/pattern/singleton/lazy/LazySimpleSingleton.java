package com.amore.spring5.pattern.singleton.lazy;

public class LazySimpleSingleton {

    static {
        System.out.println("class load....");
    }

    private static LazySimpleSingleton instance;

    private LazySimpleSingleton(){
        //防止反射暴力强吻
        if (instance!=null) {
            throw new RuntimeException("反射暴力破解");
        }
        System.out.println(Thread.currentThread().getName()+": create obj");
    }

    public static LazySimpleSingleton getInstance(){
        if (instance == null) {
            synchronized (LazySimpleSingleton.class) {
                //双重检测
                if (instance == null) {
                    instance = new LazySimpleSingleton();
                }
            }
        }
        return instance;
    }
}
