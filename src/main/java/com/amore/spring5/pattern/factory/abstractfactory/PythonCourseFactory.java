package com.amore.spring5.pattern.factory.abstractfactory;

import com.amore.spring5.pattern.factory.ICourse;
import com.amore.spring5.pattern.factory.PythonCourse;

public class PythonCourseFactory implements ICourseFactory {
    @Override
    public ICourse createCourse() {
        return new PythonCourse();
    }

    @Override
    public INode createNode() {
        return new PythonNode();
    }

    @Override
    public IVedio createVedio() {
        return new PythonVedio();
    }
}
