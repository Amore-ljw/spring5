package com.amore.spring5.singleton;

import com.amore.spring5.pattern.singleton.lazy.LazyInnerClassSingleton;

import java.lang.reflect.Constructor;

public class LazyInnerClassSingletonTest {

    public static void main(String[] args) {
        try {
            LazyInnerClassSingleton instance = LazyInnerClassSingleton.getInstance();

            Class<LazyInnerClassSingleton> clazz = LazyInnerClassSingleton.class;

            //返回所有声明的方法，报错private
            Constructor<LazyInnerClassSingleton> constructor = clazz.getDeclaredConstructor(String.class);

            //仅返回public声明的
//            Constructor<LazyInnerClassSingleton> constructor1 = clazz.getConstructor(String.class);
//
//            LazyInnerClassSingleton pubinstance = constructor1.newInstance("zl");
//
//            System.out.println(pubinstance.getUsername());

            constructor.setAccessible(true);

            LazyInnerClassSingleton newInstance = constructor.newInstance(new String("ls"));

            System.out.println(instance == newInstance);

            System.out.println(instance.getUsername());

            System.out.println(newInstance.getUsername());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
