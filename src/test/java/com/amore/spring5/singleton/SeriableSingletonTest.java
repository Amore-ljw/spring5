package com.amore.spring5.singleton;

import com.amore.spring5.pattern.singleton.seriable.SeriableSingleton;

import java.io.*;

public class SeriableSingletonTest {

    public static void main(String[] args) {

        try {
            //将对象序列化到磁盘
            SeriableSingleton instance = SeriableSingleton.getInstance();
//            File file = new File("D:/instance.json");
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:/instance.json"));
            out.writeObject(instance);
            out.flush();
            out.close();
            //反序列化读取文件到内存
            Thread.sleep(2000);
            System.out.println("========================");
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:/instance.json"));
            SeriableSingleton newInstance = (SeriableSingleton)in.readObject();
            in.close();
            System.out.println(instance);
            System.out.println(newInstance);
            System.out.println(instance == newInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
