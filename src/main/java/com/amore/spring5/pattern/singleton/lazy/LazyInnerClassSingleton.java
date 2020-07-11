package com.amore.spring5.pattern.singleton.lazy;

public class LazyInnerClassSingleton {

    static{
        System.out.println("LazyInnerClassSingleton loading...");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

    private LazyInnerClassSingleton(String username) {
        this.username = username;
        //内部类加载结束的时候已经实例化完成
        try {
            Thread.currentThread().sleep(1000);
            if (LazyClass.LAZY != null) {
                Thread.currentThread().sleep(1000);
                throw new RuntimeException("防止反射暴力破解");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static final LazyInnerClassSingleton getInstance() {
        return LazyClass.LAZY;
    }

    public static String getInfo(){
        return "ylc";
    }

    private static class LazyClass {
        static {
            System.out.println(Thread.currentThread().getName() + ": LazyClass loading ...");
        }
        private static final LazyInnerClassSingleton LAZY = new LazyInnerClassSingleton("ylc");
    }
}
