package com.amore.spring5.principle.isp.impl;

import com.amore.spring5.principle.isp.IEatAnimal;
import com.amore.spring5.principle.isp.ISwimAnimal;

public class Dog implements IEatAnimal,ISwimAnimal {
    @Override
    public void eat() {
        System.out.println("eat");
    }

    @Override
    public void swim() {
        System.out.println("swim");
    }
}
