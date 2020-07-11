package com.amore.spring5.pattern.singleton.register;

public enum EnumSingleton {
    INSTANCE;

    static {
        System.out.println("class loading...");
    }

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    EnumSingleton() {
        System.out.println(Thread.currentThread().getName() + ":" + "init object... " + name() + ":" + ordinal());
    }

}
