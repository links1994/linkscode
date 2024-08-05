package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.example.util.SqlSessionUtil;
import org.junit.Test;

import java.io.InputStream;

public class MybatisTest {

    @Test
    public void testSelectUserById() {
        SqlSession sqlSession = null;
        try {
            //读取配置文件
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            //通过SqlSessionFactoryBuilder生成qlSessionFactory
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
            //通过SqlSessionFactory#openSession()创建SqlSession对象。openSession()方法传入boolean型参数表示是否自动提交事务
            sqlSession = sqlSessionFactory.openSession(true);
            //通过SqlSession#getMapper()获取UserMapper接口的代理实现类
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectUserById(1);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void testSelectUserById2() {
        SqlSession sqlSession = null;
        try {
            sqlSession = SqlSessionUtil.getSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectUserById(1);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SqlSessionUtil.closeSqlSession(sqlSession);
        }
    }
}
