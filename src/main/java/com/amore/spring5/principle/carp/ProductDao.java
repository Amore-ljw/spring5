package com.amore.spring5.principle.carp;

public class ProductDao {

    //合成复用
    private DBConnection dbConnection;//具体的实现，这里应该抽象一下，有利于代码扩展复用

    public void setDbConnection(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void addProduct(){
        String connection = dbConnection.getMySqlConnection();
        System.out.println("添加产品："+connection);
    }
}
