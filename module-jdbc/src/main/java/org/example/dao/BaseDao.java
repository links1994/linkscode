package org.example.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao<T> {
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * 泛型的类型
     */
    private Class<T> type;

    //获取泛型的Class对象,用于查询
    public BaseDao() {
        /**
         * getClass()：获取对象的类信息包括类的名称、包信息、父类、实现的接口，泛型等
         * getGenericSuperclass()：获取泛型超类信息。其中ParameterizedType接口表示泛型类型
         * getActualTypeArguments(): 获取具体的泛型类型
         */
        Class clazz = this.getClass();
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        Type[] types = parameterizedType.getActualTypeArguments();
        this.type = (Class<T>) types[0];
    }

    /**
     * 通用的增删改操作
     */
    public int update(Connection conn, String sql, Object... params) {
        int count = 0;
        try {
            count = queryRunner.update(conn, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 获取一个对象
     */
    public T getBean(Connection conn, String sql, Object... params) {
        T t = null;
        try {
            t = queryRunner.query(conn, sql, new BeanHandler<T>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }


    /**
     * 获取所有对象
     */
    public List<T> getList(Connection conn, String sql, Object... params) {
        List<T> list = null;
        try {
            list = queryRunner.query(conn, sql, new BeanListHandler<T>(type), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取一个单一值
     */
    public Object getValue(Connection conn, String sql, Object... params) {
        Object value = null;
        try {
            queryRunner.query(conn, sql, new ScalarHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return value;
    }
}
