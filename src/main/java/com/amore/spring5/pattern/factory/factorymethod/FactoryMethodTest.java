package com.amore.spring5.pattern.factory.factorymethod;

import com.amore.spring5.pattern.factory.ICourse;

public class FactoryMethodTest {
    public static void main(String[] args) {
        ICourseFacory facory = new JavaCourseFactory();
        ICourse course = (ICourse) facory.create();
        course.record();

        facory = new PythodCourseFactory();
        course = (ICourse) facory.create();
        course.record();
    }
}
