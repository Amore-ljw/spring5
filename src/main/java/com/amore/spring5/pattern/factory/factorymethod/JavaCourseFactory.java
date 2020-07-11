package com.amore.spring5.pattern.factory.factorymethod;

import com.amore.spring5.pattern.factory.ICourse;
import com.amore.spring5.pattern.factory.JavaCourse;

public class JavaCourseFactory implements ICourseFacory<ICourse> {

    @Override
    public ICourse create() {
        return new JavaCourse();
    }
}
