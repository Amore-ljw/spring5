package com.amore.spring5.singleton;

import com.amore.spring5.pattern.singleton.hungry.HungryStaticSingleton;

import java.lang.reflect.Constructor;

public class HungrySingletonTest {

    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName() + ":" + HungryStaticSingleton.getInstance())).start();
        new Thread(() -> System.out.println(Thread.currentThread().getName() + ":" + HungryStaticSingleton.getInstance())).start();
        new Thread(() -> {
            //反射创建
            try {
                Class<?> clazz = HungryStaticSingleton.class;
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                System.out.println(Thread.currentThread().getName() + ":" + constructor.newInstance());
                System.out.println(Thread.currentThread().getName() + ":" + constructor.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();

    }

}
