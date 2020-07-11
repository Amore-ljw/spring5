package com.amore.spring5.pattern.jdbc.pool;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

public class DBConnectionPoolManager extends Pool {

    //空闲连接数
    private int num;
    //工作连接数
    private int activeNum;
    //正在使用的连接数
    private int checkedOut;
    //连接池管理类实例，单例
    private static DBConnectionPoolManager instance;
    //使用线程安全的容器来存储数据库连接对象Connection
    private Vector<Connection> freeConnections = new Vector<>();
    // 密码
    private String passWord = null;
    // 连接字符串
    private String url = null;
    // 用户名
    private String userName = null;

    //私有构造器
    private DBConnectionPoolManager() {
        //加载配置
        try {
            init();
            //按照初始化连接数创建连接对象
            System.out.println("normalConnect:" + normalConnect + " maxConnect:" + maxConnect);
            for (int i = 0; i < normalConnect; i++) {
                Connection connection = newConnection();
                if (connection != null) {
                    freeConnections.add(connection);
                    ++num;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void init() throws Exception {
//        InputStream stream = DBConnectionPoolManager.class.getResourceAsStream("database-INF.properties");
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("database-INF.properties");
        Properties properties = new Properties();
        properties.load(stream);
        this.url = properties.getProperty("url");
        this.userName = properties.getProperty("username");
        this.passWord = properties.getProperty("password");
    }

    //创建一个新连接
    private Connection newConnection() throws Exception {
        Connection connection = null;
        if (this.userName != null) {
            connection = DriverManager.getConnection(this.url, this.userName, this.passWord);
        } else {
            connection = DriverManager.getConnection(this.url);
        }
        return connection;
    }

    @Override
    public synchronized Connection getConnection() {
        Connection connection = null;
        //如果容器中有空闲连接，直接取出
        try {
            if (freeConnections.size() > 0) {
                connection = freeConnections.firstElement();
                //从容器中移除
                freeConnections.removeElementAt(0);
                num--;
                //判断连接是否有效
                if (connection.isClosed()) {
                    System.out.println("从连接池删除一个无效连接");
                    connection = getConnection();
                }
            } else if (maxConnect == 0 || activeNum < maxConnect) {// 没有空闲连接且当前连接小于最大允许值,最大值为0则不限制
                connection = newConnection();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (connection != null) {
            checkedOut++;
        }
        //todo: activeNum不懂什么意思，感觉和checkedOut一个意思
        activeNum++;
        return connection;
    }

    /**
     * 获取一个连接,并加上等待时间限制,时间为毫秒
     *
     * @param timeout(ms)
     */
    public synchronized Connection getConnection(long timeout) {
        Connection connection = null;
        long start = System.currentTimeMillis();
        while ((connection = getConnection()) == null) {
            try {
                wait(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //超时释放
            if (System.currentTimeMillis() - start >= timeout) {
                break;
            }
        }
        return connection;
    }

    @Override
    public synchronized void freeConnection(Connection con) {
        if (con != null) {
            freeConnections.addElement(con);
            num++;
            checkedOut--;
            activeNum--;
        }
    }

    //释放所有连接


    @Override
    protected synchronized void release() {
        try {
            //获取所有连接数
            Enumeration<Connection> elements = freeConnections.elements();
            //循环关闭所有连接
            while (elements.hasMoreElements()) {
                Connection connection = elements.nextElement();
                try {
                    connection.close();
                    num--;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //移除所有连接
            freeConnections.removeAllElements();
            activeNum = 0;
            num = 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            super.release();
        }
    }

    @Override
    public int getnum() {
        return num;
    }

    @Override
    public int getnumActive() {
        return activeNum;
    }

    //为保证单例，这里要做同步处理
    @Override
    public synchronized void createPool() {
        //初始化连接池管理类
        if (instance == null) {
            instance = new DBConnectionPoolManager();
            if (instance != null) {
                System.out.println("创建连接池成功");
            } else {
                System.err.println("创建连接池失败");
            }
        }
    }

    /**
     * 产生数据连接池
     *
     * @return
     */
    public static synchronized DBConnectionPoolManager getInstance() {
        if (instance == null) {
            instance = new DBConnectionPoolManager();
        }
        return instance;
    }
}
