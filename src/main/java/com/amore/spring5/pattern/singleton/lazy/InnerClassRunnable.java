package com.amore.spring5.pattern.singleton.lazy;

import java.lang.reflect.Constructor;

public class InnerClassRunnable implements Runnable {
    @Override
    public void run() {
        try {
            Class<LazyInnerClassSingleton> clazz = LazyInnerClassSingleton.class;
            //返回所有声明的方法，报错private
            Constructor<LazyInnerClassSingleton> constructor = clazz.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);
            LazyInnerClassSingleton newInstance = constructor.newInstance(Thread.currentThread().getName());
            System.out.println("newInstance name: " + newInstance.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
