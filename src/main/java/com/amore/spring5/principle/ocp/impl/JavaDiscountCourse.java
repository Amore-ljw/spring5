package com.amore.spring5.principle.ocp.impl;

public class JavaDiscountCourse extends JavaCourse{

    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    @Override
    public Double getPrice() {
        return super.getPrice()-1;
    }

    public Double getOriginPrice(){
        return super.getPrice();
    }
}
