package com.amore.spring5.pattern.singleton.hungry;

public class HungrySingleton {

    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return  instance;
    }

}
