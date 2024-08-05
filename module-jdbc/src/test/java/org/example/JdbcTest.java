package org.example;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.example.pojo.User;
import org.example.util.JDBCUtils;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JdbcTest {


    @Test
    public void testConnection() throws Exception {
        InputStream is = JdbcTest.class.getClassLoader()
                .getResourceAsStream("jdbc.properties");
        Properties prop = new Properties();
        prop.load(is);

        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        String driverClass = prop.getProperty("driverClass");

        Class.forName(driverClass);

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    private static DataSource cpds = new ComboPooledDataSource("c3p0");

    @Test
    public void testC3P0PoolConnection() throws Exception {
        Connection conn = cpds.getConnection();
        System.out.println(conn);
    }

    @Test
    public void testDBCPPoolConnection() throws Exception {
        InputStream is = JdbcTest.class.getClassLoader().getResourceAsStream("dbcp.properties");
        Properties prop = new Properties();
        prop.load(is);
        //创建DataSource对象
        DataSource dataSource = BasicDataSourceFactory.createDataSource(prop);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testDruidPoolConnection() throws Exception {
        Properties pro = new Properties();
        pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        Connection connection = ds.getConnection();
        System.out.println(connection);
    }


    @Test
    public void testQueryAll() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        String sql = "select * from user";
        List<User> users = queryAll(conn, sql, User.class);
        System.out.println(users);
    }


    /**
     * 实现增、删、改操作
     */
    public void update(Connection conn, String sql, Object... args) {
        PreparedStatement ps = null;
        try {
            //1. 获取PreparedStatement的实例并填充占位符
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            //2. 执行sql
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //3. 关闭资源
            JDBCUtils.closeResource(conn, ps);
        }

    }

    /**
     * 实现查询操作
     */
    public <T> T query(Connection conn, String sql, Class<T> clazz, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            //1. 创建PreparedStatement实例并填充占位符
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            // 2. 执行executeQuery(),得到结果集：ResultSet
            rs = ps.executeQuery();

            // 3.获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();

            // 4.通过元数据获取列数
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    // 获取列值
                    Object columnVal = rs.getObject(i + 1);
                    // 获取列的别名，列的别名和类属性名必须相同
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    // 使用反射，给对象的相应属性赋值
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnVal);

                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.关闭资源
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }

    public <T> List<T> queryAll(Connection conn, String sql, Class<T> clazz, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> resultList = new ArrayList<>();
        try {

            //1. 创建PreparedStatement实例并填充占位符
            ps = conn.prepareStatement(sql);

            // 3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            // 2. 执行executeQuery(),得到结果集：ResultSet
            rs = ps.executeQuery();

            // 3.获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();

            // 4.通过元数据获取列数
            int columnCount = rsmd.getColumnCount();
            while (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {

                    // 获取列值
                    Object columnVal = rs.getObject(i + 1);
                    // 获取列的别名，列的别名和类属性名必须相同
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    // 使用反射，给对象的相应属性赋值
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnVal);
                }
                resultList.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.关闭资源
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return resultList;
    }
}
