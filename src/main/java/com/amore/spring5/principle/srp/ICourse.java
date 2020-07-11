package com.amore.spring5.principle.srp;

public interface ICourse {

    //接口拆分:对职责进行解耦

    //获取基本信息
    String getCourseName();

    //获得视屏流
    byte[] getCourseVideo();

    //学习课程
    void studyCourse();

    //退款
    void refundCourse();

}
