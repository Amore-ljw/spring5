package com.amore.spring5.singleton;

import com.amore.spring5.pattern.singleton.register.EnumSingleton;

import java.io.*;
import java.lang.reflect.Constructor;

public class EnumSingletonTest {

    public static void main(String[] args) {
        //测试反射实例化枚举类
        EnumSingleton instance = EnumSingleton.INSTANCE;
        System.out.println(instance.name() + " : " + instance.ordinal());
        try {
            Class<EnumSingleton> clazz = EnumSingleton.class;
            Constructor<EnumSingleton> constructor = clazz.getDeclaredConstructor(String.class,int.class);
            constructor.setAccessible(true);
            EnumSingleton newInstance = constructor.newInstance();
            //Constructor -> newInstance(...) -> if ((clazz.getModifiers() & Modifier.ENUM) != 0)
            //throw new IllegalArgumentException("Cannot reflectively create enum objects");
            System.out.println(instance == newInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        ============================================

        //测试反序列化创建枚举实例
        try {
            //序列化到文件
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:/enum.obj"));
            out.writeObject(instance);
            out.flush();
            out.close();
            //反序列化
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:/enum.obj"));
            EnumSingleton newInstance = (EnumSingleton) in.readObject();
            System.out.println(instance == newInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
