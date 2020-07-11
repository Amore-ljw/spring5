package com.amore.spring5.pattern.jdbc;

import com.amore.spring5.pattern.jdbc.pool.DBConnectionPoolManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplate {

    public static <T> T query(String sql, IRowMapper<T> mapper, Object... params) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        DBConnectionPoolManager instance = null;
        try {
//            connection = JDBCUtil.getConnection();
            instance = DBConnectionPoolManager.getInstance();
            connection = instance.getConnection();
            System.out.println("num:" + instance.getnum() + " active:" + instance.getnumActive());
            ps = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            resultSet = ps.executeQuery();
            return mapper.rowMapper(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            JDBCUtil.close(resultSet,ps,connection);
            instance.freeConnection(connection);
        }
        return null;
    }

    /*public static List query(String sql, IRowMapper mapper ,Object... params) {
        List list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);
            if (params != null) {
                for (int  i=0;i<params.length;i++) {
                    ps.setObject(i+1,params[i]);
                }
            }
            resultSet = ps.executeQuery();
            return (List)mapper.rowMapper(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(resultSet,ps,connection);
        }
        return list;
    }*/

    //重载该方法，处理响应结果集
    public static List<Student> query(String sql, Object... params) {
        List<Student> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);
            if (params != null) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                Student student = new Student(id, name, age);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(resultSet, ps, connection);
        }
        return list;
    }


    public static int update(String sql, Object... obj) {
        Connection conn = null;
        PreparedStatement ps = null;
        if (sql != null) {
            try {
                conn = JDBCUtil.getConnection();
                ps = conn.prepareStatement(sql);
                if (obj != null) {
                    for (int i = 0; i < obj.length; i++) {
                        ps.setObject(i + 1, obj[i]);
                    }
                }
                int statusCode = ps.executeUpdate();
                return statusCode;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                JDBCUtil.close(null, ps, conn);
            }
        }
        return 0;
    }

    public List<Student> list() {
        String sql = "SELECT * FROM  t_student ";
        return JdbcTemplate.query(sql);
    }

    public Student get(Long id) {
        String sql = "SELECT * FROM t_student WHERE id=?";
        List<Student> list = JdbcTemplate.query(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    public void save(Student stu) {
        String sql = "INSERT INTO t_student(name,age) VALUES(?,?)";
        Object[] params = new Object[]{stu.getName(), stu.getAge()};
        int update = JdbcTemplate.update(sql, params);
        System.out.println(update);
    }

    // 修改学生信息
    public void update(Student stu) {
        String sql = "UPDATE t_student SET name = ?,age = ? WHERE id = ?";
        Object[] params = new Object[]{stu.getName(), stu.getAge(), stu.getId()};
        int update = JdbcTemplate.update(sql, params);
        System.out.println(update);
    }

    public static void main(String[] args) {
        //测试重载的query方法
//        List<Student> result = JdbcTemplate.query("SELECT * FROM  t_student ",
//                (ResultSet rs) -> {
//                    List<Student> list = new ArrayList<>();
//                    while (rs.next()){
//                        long id = rs.getLong("id");
//                        String name = rs.getString("name");
//                        int age = rs.getInt("age");
//                        Student student = new Student(id, name, age);
//                        list.add(student);
//                    }
//                    return list;
//                },null);
//        System.out.println(result.size());
//      ==============================================================================
        Map<String, Object> res = JdbcTemplate.query("SELECT * FROM  t_student ", (rs) -> {
            Map<String, Object> map = new HashMap<>();
            List<Student> list = new ArrayList<>();
            if (rs != null) {
                while (rs.next()) {
                    long id = rs.getLong("id");
                    String name = rs.getString("name");
                    int age = rs.getInt("age");
                    Student student = new Student(id, name, age);
                    list.add(student);
                }
            }
            map.put("list", list);
            map.put("size", list.size());
            return map;
        }, null);
        System.out.println(res.get("size") + ":" + res.get("list"));
//      ==============================================================================
//        JdbcTemplate template = new JdbcTemplate();
//        List<Student> list = template.list();
//        System.out.println(list.size());
//        Student student = template.get(3L);
//        System.out.println(student.getId()+":"+student.getName()+":"+student.getAge());
//        Student stu = new Student();
//        stu.setAge(30);
//        stu.setName("ylc");
//        stu.setId(4L);
//        template.save(stu);
//        template.update(stu);
//      ==============================================================================


    }
}
