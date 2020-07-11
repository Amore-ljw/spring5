package com.amore.spring5.pattern.factory.abstractfactory;

public class AbstractFactoryTest {

    public static void main(String[] args) {
        ICourseFactory factory = new JavaCourseFactory();
        factory.createCourse().record();
        factory.createNode().node();
        factory.createVedio().video();

        factory = new PythonCourseFactory();
        factory.createVedio().video();
        factory.createNode().node();
        factory.createCourse().record();
    }
}
