package com.amore.spring5.singleton;

import com.amore.spring5.pattern.singleton.hungry.HungryStaticSingleton;
import com.amore.spring5.pattern.singleton.lazy.ExectorThread;

import java.lang.reflect.Constructor;

public class LazySimpleSingletonTest {

    public static void main(String[] args) {
        new Thread(new ExectorThread()).start();
        new Thread(new ExectorThread()).start();

//        Class clazz = HungryStaticSingleton.class;
//
//        try {
//            Constructor constructor = clazz.getDeclaredConstructor();
//            constructor.setAccessible(true);
//            System.out.println(constructor.newInstance());
//            System.out.println(constructor.newInstance());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        System.out.println("end...");
    }
}
