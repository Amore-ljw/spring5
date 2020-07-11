package com.amore.spring5.principle.carp;

public class OracleConnection extends DBConnection{
    @Override
    String getConnection() {
        return "Oracle Connection";
    }
}
