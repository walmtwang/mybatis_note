package cn.walmt.first;

import cn.walmt.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by walmt on 2018/1/12.
 */
public class MybatisFirst {

    public static void main(String[] args) throws IOException {
        new MybatisFirst().updateUserTest();
    }

    // 根据id查询用户信息，得到一条记录结果
    public void findUserByIdTest() throws IOException {

        //mybatis配置文件
        String resources = "SqlmapConfig.xml";
        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resources);

        // 创建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 通过SqlSession操作数据库
        // 第一个参数：映射文件中statement的id，等于=namespace + "." + statement的id
        // 第二个参数：指定和映射文件中所匹配的parameterType类型的参数
        // sqlSession.selectOne结果是与映射文件中所匹配的resultType类型的对象
        User user = sqlSession.selectOne("test.findUserById", 1);

        System.out.println(user);

        //释放资源
        sqlSession.close();
    }

    // 根据用户名称模糊查询用户列表
    public void findUserByNameTest() throws IOException {
        //mybatis配置文件
        String resources = "SqlmapConfig.xml";
        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resources);

        // 创建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // list中的user和映射文件中resultType所指定的类型一致
        List<User> userList = sqlSession.selectList("test.findUserByName", "a");
        System.out.println(userList);
        sqlSession.close();
    }

    // 添加用户信息
    public void insertUserTest() throws IOException {
        //mybatis配置文件
        String resources = "SqlmapConfig.xml";
        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resources);

        // 创建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //插入用户对象
        User user = new User();
        user.setUsername("老王");
        user.setSex("1");
        user.setBirthday(new Date());
        user.setAddress("老王家");
        // list中的user和映射文件中resultType所指定的类型一致
        sqlSession.insert("test.insertUser", user);
        System.out.println(user);
        // 提交事务
        sqlSession.commit();
        // 关闭会话
        sqlSession.close();
    }

    // 根据id删除用户
    public void deleteUserTest() throws IOException {
        //mybatis配置文件
        String resources = "SqlmapConfig.xml";
        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resources);

        // 创建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 传入id删除用户
        sqlSession.delete("test.deleteUser", 8);
        // 提交事务
        sqlSession.commit();
        // 关闭会话
        sqlSession.close();
    }

    // 更新用户信息
    public void updateUserTest() throws IOException {
        //mybatis配置文件
        String resources = "SqlmapConfig.xml";
        //得到配置文件流
        InputStream inputStream = Resources.getResourceAsStream(resources);

        // 创建会话工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 通过工厂得到SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 更新用户信息
        User user = new User();
        user.setId(6);
        user.setUsername("小王");
        user.setBirthday(new Date());
        user.setSex("2");
        user.setAddress("小王家");
        sqlSession.update("test.updateUser", user);

        // 提交事务
        sqlSession.commit();
        // 关闭会话
        sqlSession.close();
    }
}
