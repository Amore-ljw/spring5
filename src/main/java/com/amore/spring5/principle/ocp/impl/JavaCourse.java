package com.amore.spring5.principle.ocp.impl;

import com.amore.spring5.principle.ocp.ICourse;

/**
 * 实现课程接口：优惠价格的需求，不能直接修改: getPrice()
 */
public class JavaCourse implements ICourse {
    private Integer id;
    private String name;
    private Double price;

    public JavaCourse() {
    }

    public JavaCourse(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }
}
