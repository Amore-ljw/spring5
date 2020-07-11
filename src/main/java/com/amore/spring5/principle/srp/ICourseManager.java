package com.amore.spring5.principle.srp;

//接口拆分:对职责进行解耦
public interface ICourseManager {
    //学习课程
    void studyCourse();

    //退款
    void refundCourse();
}
