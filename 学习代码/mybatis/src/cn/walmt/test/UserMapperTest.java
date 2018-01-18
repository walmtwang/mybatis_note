package cn.walmt.test;

import cn.walmt.dao.UserDao;
import cn.walmt.dao.UserDaoImpl;
import cn.walmt.mapper.UserMapper;
import cn.walmt.pojo.User;
import cn.walmt.pojo.UserCustom;
import cn.walmt.pojo.UserQueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by walmt on 2018/1/14.
 */
public class UserMapperTest {

    private SqlSessionFactory sqlSessionFactory;

    public static void main(String[] args) throws Exception {
        UserMapperTest userMapperTest = new UserMapperTest();
        userMapperTest.setUp();
        userMapperTest.testFindUserList();
    }

    // 此方法是在执行testFindUserById之前执行
    public void setUp() throws Exception {
        // 创建sqlSessionFactory

        //mybatis配置文件
        String resources = "SqlmapConfig.xml";
        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resources);

        // 创建会话工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public void testFindUserById() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 创建UserMapper的对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 调用UserMapper的方法
        User user = userMapper.findUserById(1);

        sqlSession.close();

        System.out.println(user);
    }

    public void testFindUserByIdResultMap() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 创建UserMapper的对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 调用UserMapper的方法
        User user = userMapper.findUserByIdResultMap(1);

        sqlSession.close();

        System.out.println(user);
    }

    public void testFindUserByName() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 创建UserMapper的对象,mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 调用UserMapper的方法
        List<User> userList = userMapper.findUserByName("a");

        sqlSession.close();

        System.out.println(userList);
    }

    // 用户信息综合查询
    public void testFindUserList() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 创建UserMapper的对象,mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 创建包装对象,设置查询条件
        UserQueryVo userQueryVo = new UserQueryVo();
        UserCustom userCustom = new UserCustom();
        // 由于这里使用了动态sql，如果不设置某个值，条件不会拼接在sql中
//        userCustom.setSex("1");
        userCustom.setUsername("王");
        userQueryVo.setUserCustom(userCustom);
        // 传入多个id
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);

        // 将ids传入statement中
        userQueryVo.setIds(ids);

        // 调用UserMapper的方法
        List<UserCustom> userListr = userMapper.findUserList(userQueryVo);

        sqlSession.close();

        System.out.println(userListr);
    }

    // 用户信息综合查询
    public void testFindUserCount() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 创建UserMapper的对象,mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 创建包装对象,设置查询条件
        UserQueryVo userQueryVo = new UserQueryVo();
        UserCustom userCustom = new UserCustom();
        userCustom.setSex("1");
        userCustom.setUsername("王");
        userQueryVo.setUserCustom(userCustom);

        // 调用UserMapper的方法
        Integer count = userMapper.findUserCount(userQueryVo);

        sqlSession.close();

        System.out.println(count);
    }

    // 用户信息综合查询
    public void testFindUserListByMap() throws Exception {

        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 创建UserMapper的对象,mybatis自动生成mapper代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 创建hashMap，放入需要的属性
        Map<String, Object> map = new HashMap<>();
        map.put("sex", "1");
        map.put("username", "王");

        // 调用UserMapper的方法
        List<UserCustom> userListr = userMapper.findUserListByMap(map);

        sqlSession.close();

        System.out.println(userListr);
    }
}
