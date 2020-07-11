package com.amore.spring5.pattern.factory.abstractfactory;

import com.amore.spring5.pattern.factory.ICourse;
import com.amore.spring5.pattern.factory.JavaCourse;

public class JavaCourseFactory implements ICourseFactory{
    @Override
    public ICourse createCourse() {
        return new JavaCourse();
    }

    @Override
    public INode createNode() {
        return new JavaNode();
    }

    @Override
    public IVedio createVedio() {
        return new JavaVedio();
    }
}
