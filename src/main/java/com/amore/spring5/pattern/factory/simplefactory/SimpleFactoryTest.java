package com.amore.spring5.pattern.factory.simplefactory;

import com.amore.spring5.pattern.factory.ICourse;
import com.amore.spring5.pattern.factory.JavaCourse;
import com.amore.spring5.pattern.factory.PythonCourse;

public class SimpleFactoryTest {

    public static void main(String[] args) {
        CourseFactory<ICourse> factory = new CourseFactory<>();
        ICourse javacourse = factory.create(JavaCourse.class);
        ICourse pythoncourse = factory.create(PythonCourse.class);
        javacourse.record();
        pythoncourse.record();
    }
}
