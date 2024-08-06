package org.example;

import lombok.val;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.example.util.SqlSessionUtil;
import org.junit.Test;

import java.io.InputStream;
import java.util.*;

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

    @Test
    public void testQueryUserWithIf() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = SqlSessionUtil.getSqlSession();
            // 获取UserMapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 依据用户名和密码查询用户
            List<User> userList = userMapper.queryUserWithIf("lucy", "");
            //List<User> userList = userMapper.queryUserWithIf("", "123456");
            //List<User> userList = userMapper.queryUserWithIf("", "");
            //List<User> userList = userMapper.queryUserWithIf("lucy","123456");
            // 遍历打印查询结果
            Iterator<User> iterator = userList.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            SqlSessionUtil.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testQueryUserWithWhere() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = SqlSessionUtil.getSqlSession();
            // 获取UserMapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 依据用户名和密码查询用户
            List<User> userList = userMapper.queryUserWithWhere("lucy", "");
            //List<User> userList = userMapper.queryUserWithWhere("", "123456");
            //List<User> userList = userMapper.queryUserWithWhere("", "");
            //List<User> userList = userMapper.queryUserWithWhere("lucy","123456");
            // 遍历打印查询结果
            Iterator<User> iterator = userList.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            SqlSessionUtil.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testQueryUserWithChoose() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = SqlSessionUtil.getSqlSession();
            // 获取UserMapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 依据用户名和密码查询用户
            List<User> userList = userMapper.queryUserWithChoose("", "123456");
            //List<User> userList = userMapper.queryUserWithChoose("lucy", "");
            //List<User> userList = userMapper.queryUserWithChoose("", "");
            // 遍历打印查询结果
            Iterator<User> iterator = userList.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            SqlSessionUtil.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testUpdateUserWithSet() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = SqlSessionUtil.getSqlSession();
            // 获取UserMapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建用户对象
            User user = new User(1, "tutu", "666999", "male");
            // 更新用户
            int result = userMapper.updateUserWithSet(user);
            // 打印更新结果
            if(result > 0){
                System.out.println("用户更新成功");
            }else{
                System.out.println("用户更新失败");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            SqlSessionUtil.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testUpdateUserWithTrim() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = SqlSessionUtil.getSqlSession();
            // 获取UserMapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建用户对象
            User user = new User(1, "wawa", "666666", "male");
            // 更新用户
            int result = userMapper.updateUserWithTrim(user);
            // 打印更新结果
            if(result > 0){
                System.out.println("用户更新成功");
            }else{
                System.out.println("用户更新失败");
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            SqlSessionUtil.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testQueryUserWithBind() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = SqlSessionUtil.getSqlSession();
            // 获取UserMapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 查询用户
            List<User> userList = userMapper.queryUserWithBind("xi");
            // 遍历打印查询结果
            Iterator<User> iterator = userList.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            SqlSessionUtil.closeSqlSession(sqlSession);
        }
    }

    // 测试foreach遍历List
    @Test
    public void testQueryUserWithForeach1() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = SqlSessionUtil.getSqlSession();
            // 获取UserMapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建列表保存用户id
            List<Integer> userIDList = new ArrayList<Integer>();
            // 向列表中添加用户id
            userIDList.add(1);
            userIDList.add(2);
            userIDList.add(3);
            // 查询用户
            List<User> userList = userMapper.queryUserWithForeach1(userIDList);
            // 遍历打印查询结果
            Iterator<User> iterator = userList.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            SqlSessionUtil.closeSqlSession(sqlSession);
        }
    }

    // 测试foreach遍历数组
    @Test
    public void testQueryUserWithForeach2() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = SqlSessionUtil.getSqlSession();
            // 获取UserMapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建数组保存用户id
            int[] userIDArray = {1,2,3};
            // 查询用户
            List<User> userList = userMapper.queryUserWithForeach2(userIDArray);
            // 遍历打印查询结果
            Iterator<User> iterator = userList.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            SqlSessionUtil.closeSqlSession(sqlSession);
        }
    }

    // 测试foreach与Map的使用
    @Test
    public void testQueryUserWithForeach3() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = SqlSessionUtil.getSqlSession();
            // 获取UserMapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 创建Map
            val userMap = new HashMap<String, Object>();
            // 向Map中添加数据
            userMap.put("gender", "female");
            List<Integer> userIDList = new ArrayList<Integer>();
            userIDList.add(1);
            userIDList.add(2);
            userIDList.add(3);
            // 向Map中添加数据
            userMap.put("userIDList",userIDList);
            // 查询用户
            List<User> userList = userMapper.queryUserWithForeach3(userMap);
            // 遍历打印查询结果
            Iterator<User> iterator = userList.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            SqlSessionUtil.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testQueryUserWithInclude() {
        SqlSession sqlSession = null;
        try {
            // 获取SqlSession
            sqlSession = SqlSessionUtil.getSqlSession();
            // 获取UserMapper
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            // 查询用户
            List<User> userList = userMapper.queryUserWithInclude("pepe", "456123");
            // 遍历打印查询结果
            Iterator<User> iterator = userList.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            SqlSessionUtil.closeSqlSession(sqlSession);
        }
    }
}
