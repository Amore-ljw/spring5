package com.amore.spring5.pattern.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCUtil {
    private static Properties properties;

    private JDBCUtil(){
        System.err.println("init JDBCUtil");
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    static{
        //加载数据库驱动
        try {
//            Class.forName("com.mysql.jdbc.Driver");
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream stream = classLoader.getResourceAsStream("db.properties");
            properties = new Properties();
            properties.load(stream);
            System.out.println(properties.getProperty("driverClassName"));
            Class.forName(properties.getProperty("driverClassName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection(){
        try {
//            return DriverManager.getConnection("jdbc:mysql://192.168.23.182/mysql", "root", "123456");
            return DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("username"),properties.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //释放资源
    public static void close(ResultSet resultSet, Statement statement,Connection conn){
        try {
            if (resultSet!=null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement!=null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn!=null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
//        JDBCUtil util = new JDBCUtil();
        Connection connection = JDBCUtil.getConnection();
        System.out.println(connection);

    }

    // 增加学生信息
    public void save(Student stu) {
        String sql = "INSERT INTO t_student(name,age) VALUES(?,?)";
        Connection conn = null;
        PreparedStatement ps=null;
        try {
            conn = JDBCUtil.getConnection();
            // 3. 创建语句对象
            ps = conn.prepareStatement(sql);
            ps.setObject(1, stu.getName());
            ps.setObject(2, stu.getAge());
            // 4. 执行SQL语句
            ps.executeUpdate();
            // 5. 释放资源
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, ps, conn);
        }

    }

    // 删除学生信息
    public void delete(Long id) {
        String sql = "DELETE  FROM t_student WHERE id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn=JDBCUtil.getConnection();
            // 3. 创建语句对象
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            // 4. 执行SQL语句
            ps.executeUpdate();
            // 5. 释放资源
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, ps, conn);
        }

    }

    // 修改学生信息
    public void update(Student stu) {
        String sql = "UPDATE t_student SET name=?,age=? WHERE id=?";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn=JDBCUtil.getConnection();
            // 3. 创建语句对象
            ps = conn.prepareStatement(sql);
            ps.setObject(1, stu.getName());
            ps.setObject(2, stu.getAge());
            ps.setObject(3, stu.getId());
            // 4. 执行SQL语句
            ps.executeUpdate();
            // 5. 释放资源
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(null, ps, conn);
        }

    }

    public Student get(Long id) {
        String sql = "SELECT * FROM t_student WHERE id=?";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ps=null;
        try {
            conn = JDBCUtil.getConnection();
            // 3. 创建语句对象
            ps = conn.prepareStatement(sql);
            ps.setObject(1, id);
            // 4. 执行SQL语句
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                Student stu = new Student(id, name, age);
                return stu;
            }
            // 5. 释放资源
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return null;
    }

    public List<Student> list() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM t_student ";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        PreparedStatement ps=null;
        try {
            conn=JDBCUtil.getConnection();
            // 3. 创建语句对象
            ps = conn.prepareStatement(sql);
            // 4. 执行SQL语句
            rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                Student stu = new Student(id, name, age);
                list.add(stu);
            }
            // 5. 释放资源
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, ps, conn);
        }
        return list;
    }


}
