package cn.walmt.test;

import cn.walmt.dao.UserDao;
import cn.walmt.dao.UserDaoImpl;
import cn.walmt.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Created by walmt on 2018/1/14.
 */
public class UserDaoImplTest {

    private SqlSessionFactory sqlSessionFactory;

    public static void main(String[] args) throws Exception {
        UserDaoImplTest userDaoImplTest = new UserDaoImplTest();
        userDaoImplTest.setUp();
        userDaoImplTest.testFindUserById();
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
        // 创建UserDao的对象
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);

        // 调用UserDao的方法
        User user = userDao.findUserById(1);

        System.out.println(user);

    }
}
