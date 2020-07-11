package com.amore.spring5.principle.dip.impl;

/**
 * DIP(依赖倒置demo)
 */
public class Tom {

    public void studyJavaCourse(){
        System.out.println("study Java");
    }
    public void studyPythonCourse(){
        System.out.println("study Python");
    }

    public static void main(String[] args) {
        //当业务扩展时候，需要从底层到顶层依次修改
        //要面向接口编程，先顶层再细节地设计代码结构
        Tom tom = new Tom();
        tom.studyJavaCourse();
        tom.studyPythonCourse();

        //修改依赖后的代码:不需要修改底层代码，只需要新增实现类
        StudyClient client = new StudyClient();
        client.study(new JavaStudy());
        client.study(new PythonStudy());

        //构造注入
        StudyClient clientCon = new StudyClient(new JavaStudy());
        clientCon.study();

        //set注入
        client.setStudy(new PythonStudy());
        client.study();
    }
}
