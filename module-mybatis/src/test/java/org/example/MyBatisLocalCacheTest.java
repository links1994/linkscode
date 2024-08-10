package org.example;

import org.apache.log4j.Logger;
import org.example.mapper.UserLocalCacheMapper;
import org.example.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.example.util.SqlSessionUtil;
import org.junit.Test;

public class MyBatisLocalCacheTest {

    private static final Logger logger = Logger.getLogger(MyBatisLocalCacheTest.class);

    @Test
    public void testQueryUserById1() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = SqlSessionUtil.getSqlSession();
            // 获取UserLocalCacheMapper
            UserLocalCacheMapper userLocalCacheMapper = sqlSession.getMapper(UserLocalCacheMapper.class);
            // 用户id
            int userID = 1;
            // 第一次查询id为1的用户
            User user = userLocalCacheMapper.queryUserById(userID);
            // 打印查询结果
            System.out.println("第一次查询结果："+user);
            sqlSession.commit();
            // 第二次查询id为1的用户
            user = userLocalCacheMapper.queryUserById(userID);
            // 打印查询结果
            System.out.println("第二次查询结果："+user);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            SqlSessionUtil.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testQueryUserById2() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = SqlSessionUtil.getSqlSession();
            // 获取UserLocalCacheMapper
            UserLocalCacheMapper userLocalCacheMapper = sqlSession.getMapper(UserLocalCacheMapper.class);
            // 用户id
            int userID = 1;
            // 第一次查询id为1的用户
            User user = userLocalCacheMapper.queryUserById(userID);
            // 打印查询结果
            System.out.println("第一次查询结果："+user);
            // 创建用户对象
            User u = new User(2, "tata", "666777", "male");
            // 更新id为2的用户
            userLocalCacheMapper.updateUser(u);
            // 第二次查询id为1的用户
            user = userLocalCacheMapper.queryUserById(userID);
            // 打印查询结果
            System.out.println("第二次查询结果："+user);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            SqlSessionUtil.closeSqlSession(sqlSession);
        }
    }

    /**
     * 验证性实验，非书中内容。
     * 该验证与testQueryUserById1()相对于
     * 在第二次查询前重新获取SqlSession和UserLocalCacheMapper
     */
    @Test
    public void testQueryUserById3() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = SqlSessionUtil.getSqlSession();
            // 获取UserLocalCacheMapper
            UserLocalCacheMapper userLocalCacheMapper = sqlSession.getMapper(UserLocalCacheMapper.class);
            // 用户id
            int userID = 1;
            // 第一次查询id为1的用户
            User user = userLocalCacheMapper.queryUserById(userID);
            // 打印查询结果
            System.out.println("第一次查询结果："+user);
            // 注意：重新获取SqlSession
            sqlSession = SqlSessionUtil.getSqlSession();
            // 注意：重新获取UserLocalCacheMapper
            userLocalCacheMapper = sqlSession.getMapper(UserLocalCacheMapper.class);
            // 第二次查询id为1的用户
            user = userLocalCacheMapper.queryUserById(userID);
            // 打印查询结果
            System.out.println("第二次查询结果："+user);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            SqlSessionUtil.closeSqlSession(sqlSession);
        }
    }

}
