package com.amore.spring5.principle.carp;

/**
 * Composite/Aggregate Reuse Principle CARP(合成复用原则：尽量使用对象的组合、聚合关系，而不是继承关系达到复用的目的)
 */
public abstract class DBConnection {

    String getMySqlConnection() {
        return "MySql 数据库链接";
    }

    abstract String getConnection();

}
