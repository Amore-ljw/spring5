package com.amore.spring5.principle.carp;

public class MySqlConnection extends DBConnection {
    @Override
    String getConnection() {
        return "MySql Connection";
    }
}
