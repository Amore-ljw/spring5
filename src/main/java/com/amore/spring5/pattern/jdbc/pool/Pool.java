package com.amore.spring5.pattern.jdbc.pool;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接池：规避频繁构建数据库链接对象,造成的资源浪费
 */
public abstract class Pool {

    //连接池实例
    private static Pool pool = null;

    //数据库驱动类名
    protected String driverName = null;

    //驱动实例
    protected Driver driver = null;

    //数据库最大连接数
    protected int maxConnect = 100;

    //保持连接数
    protected int normalConnect = 10;

    protected Pool() {
        try {
            System.out.println("Pool create start...");
            init();
            loadDriver();
        } catch (Exception e) {
            System.out.println("Pool create error...");
            e.printStackTrace();
        }
    }

    //初始化数据库驱动
    private void init() throws Exception {
        System.out.println("Pool init start...");
        //读取配置文件初始化属性
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("database-INF.properties");
//        InputStream stream = Pool.class.getResourceAsStream("database-INF.properties");
        Properties properties = new Properties();
        properties.load(stream);
        this.driverName = properties.getProperty("driverClassName");
        this.maxConnect = properties.getProperty("maxConnect") == null ? this.maxConnect : Integer.parseInt(properties.getProperty("maxConnect"));
        this.normalConnect = properties.getProperty("normalConnect") == null ? this.normalConnect : Integer.parseInt(properties.getProperty("normalConnect"));
    }

    //注册驱动
    protected void loadDriver() {
        try {
            this.driver = (Driver) Class.forName(this.driverName).newInstance();
            DriverManager.registerDriver(driver);
            System.out.println("数据库驱动注册成功:" + driverName);
        } catch (Exception e) {
            System.err.println("数据库驱动注册异常:" + driverName + " 异常：" + e);
        }
    }

    //初始化连接池实例
    public static synchronized Pool getPool() throws Exception {
        if (pool==null) {
            pool = (Pool)Class.forName("com.amore.spring5.pattern.jdbc.pool.Pool").newInstance();
        }
        return pool;
    }

    //释放所有数据库链接,撤销驱动注册,单例
    protected synchronized void release() {
        try {
            DriverManager.deregisterDriver(driver);
            System.out.println("撤销 JDBC 驱动程序"+driver.getClass().getName());
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("无法撤销 JDBC 驱动程序的注册:"+driver.getClass().getName());
        }

    }

    //获取链接,从连接池获取对象
    public abstract Connection getConnection();

    //释放链接,将Connection归还数据库连接池
    public abstract void freeConnection(Connection con);

    //获取当前空闲链接数
    public abstract int getnum();

    //获取当前工作链接数
    public abstract int getnumActive();

    //创建数据库链接
    public abstract void createPool();



}
