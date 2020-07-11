package com.amore.spring5.principle.lod;


/**
 * Law of Demeter LoD(迪米特原则:一个对象应该对其他对象保持最少的了解)
 * Least Knowledge Principle LPK(最少知道原则:尽量降低类与类之间的耦合度)
 * 只和朋友交流，不和陌生人说话。出现在成员变量、方法的输入、输出参数中的类都可以称为成员朋友类，而出现在方法体内部的类不属于朋友类
 */
public class LawOfDemeterTest {

    public static void main(String[] args) {
        Boss boss = new Boss();
        TeamLeader leader = new TeamLeader();
        boss.checkNumberOfCourse(leader);
    }
}
