package com.amore.spring5.principle.dip.impl;


import com.amore.spring5.principle.dip.IStudy;

public class PythonStudy implements IStudy {
    @Override
    public void study() {
        System.out.println("Python study");
    }
}
