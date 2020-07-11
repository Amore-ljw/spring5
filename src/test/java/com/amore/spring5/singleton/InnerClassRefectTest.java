package com.amore.spring5.singleton;

import com.amore.spring5.pattern.singleton.lazy.InnerClassRunnable;
import com.amore.spring5.pattern.singleton.lazy.LazyInnerClassSingleton;

public class InnerClassRefectTest {

    public static void main(String[] args) {
//        try {
//            Thread.currentThread().getContextClassLoader().loadClass("com.amore.spring5.pattern.singleton.lazy.LazyInnerClassSingleton");
//            System.out.println(LazyInnerClassSingleton.getInfo());
//            Thread.sleep(2000);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        int i = 10;
        while (i > 5) {
            new Thread(new InnerClassRunnable()).start();
            i--;
        }
    }

}
