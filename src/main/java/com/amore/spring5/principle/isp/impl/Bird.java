package com.amore.spring5.principle.isp.impl;

import com.amore.spring5.principle.isp.IEatAnimal;
import com.amore.spring5.principle.isp.IFlyAnimal;

public class Bird implements IEatAnimal,IFlyAnimal {
    @Override
    public void eat() {
        System.out.println("eat");
    }

    @Override
    public void fly() {
        System.out.println("fly");
    }
}
