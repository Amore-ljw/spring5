package com.amore.spring5.pattern.jdbc;

import java.sql.ResultSet;
import java.util.List;

@FunctionalInterface
public interface IRowMapper<T> {
    //处理结果集
//    List rowMapper(ResultSet set) throws Exception;

    T rowMapper(ResultSet set) throws Exception;
}
