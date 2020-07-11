package com.amore.spring5.principle.srp.impl;

public class Course {

    public void study(String courseName) {
        if ("直播".equalsIgnoreCase(courseName)) {
            System.out.println(courseName + "不能快进");
        } else {
            System.out.println(courseName + "可重复看");
        }
    }
    //方法层面的单一职责设计
    public void modifyUserInfo(String username,String... fileds){
        username = "xxx";
        //todo: 属性修改复制
    }

    //方法拆分
    public void modifyUserName(String username){
        username = "xxx";
    }
    public void modifyAddress(String address){
        address = "xxx";
    }

    public static void main(String[] args) {
        //该类承担了两种职责，对课程进行加密必须修改代码
        Course course = new Course();
        course.study("直播");
        course.study("其他");
    }
}
