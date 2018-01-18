package cn.walmt.test;

import cn.walmt.mapper.OrdersMapperCustom;
import cn.walmt.mapper.UserMapper;
import cn.walmt.pojo.Orders;
import cn.walmt.pojo.OrdersCustom;
import cn.walmt.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * Created by walmt on 2018/1/15.
 */
public class OrdersMapperCustomTest {

    private SqlSessionFactory sqlSessionFactory;

    public static void main(String[] args) throws Exception {
        OrdersMapperCustomTest ordersMapperCustomTest = new OrdersMapperCustomTest();
        ordersMapperCustomTest.setUp();
        ordersMapperCustomTest.testCache2();
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

    public void testFindOrdersUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 创建代理对象
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);

        // 调用mapper方法
        List<OrdersCustom> list = ordersMapperCustom.findOrdersUser();

        System.out.println(list);

        sqlSession.close();
    }

    public void testFindOrdersUserResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 创建代理对象
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);

        // 调用mapper方法
        List<Orders> list = ordersMapperCustom.findOrdersUserResultMap();

        System.out.println(list);

        sqlSession.close();
    }


    public void testFindOrdersAndOrderDetailResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 创建代理对象
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);

        // 调用mapper方法
        List<Orders> list = ordersMapperCustom.findOrdersAndOrderDetailResultMap();

        System.out.println(list);

        sqlSession.close();
    }

    public void testUserAndItemsResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 创建代理对象
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);

        // 调用mapper方法
        List<User> list = ordersMapperCustom.findUserAndItemsResultMap();

        System.out.println(list);

        sqlSession.close();
    }

    // 查询订单关联查询用户，用户信息使用延迟加载
    public void testFindOrdersUserLazyLoading() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 创建代理对象
        OrdersMapperCustom ordersMapperCustom = sqlSession.getMapper(OrdersMapperCustom.class);

        // 调用mapper方法
        List<Orders> list = ordersMapperCustom.findOrdersUserLazyLoading();

        for (Orders orders: list) {
            // 执行getUser()去查询用户信息，这里实现按需加载
            User user = orders.getUser();
            System.out.println(user);
        }

        sqlSession.close();
    }

    // 一级缓存测试
    public void testCache1() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession(); // 创建代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        // 下边查询使用SqlSession
        // 第一次发起请求，查询id为1的用户
        User user1 = userMapper.findUserById(1);
        System.out.println(user1);

//        // 如果sqlSession去执行commit操作（执行插入、更新、删除），清空SqlSession中的一级缓存，这样做的目的为了让缓存中存储的是最新信息，避免脏读。
//        // 更新user1的信息
//        user1.setUsername("user2");
//        userMapper.updateUser(user1);
//        // 执行commit操作去清空缓存
//        sqlSession.commit();

        // 第二次发起请求，查询id为1的用户
        User user2 = userMapper.findUserById(1);
        System.out.println(user2);

        sqlSession.close();
    }

    // 二级缓存测试
    public void testCache2() throws Exception {
        SqlSession sqlSession1 = sqlSessionFactory.openSession(); // 创建代理对象
        SqlSession sqlSession2 = sqlSessionFactory.openSession(); // 创建代理对象
        SqlSession sqlSession3 = sqlSessionFactory.openSession(); // 创建代理对象

        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        // 第一次发起请求，查询id为1的用户
        User user1 = userMapper1.findUserById(1);
        System.out.println(user1);
        user1.setUsername("222");

        // 这里执行关闭操作，将sqlSession中的数据写到二级缓存区域
        sqlSession1.close();

//        // 使用sqlSession3执行commit操作
//        UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
//        User user = userMapper3.findUserById(1);
//        user.setUsername("123");
//        userMapper3.updateUser(user);
//        // 执行提交，清空UserMapper下边的二级缓存
//        sqlSession3.commit();
//        sqlSession3.close();

        // 第二次发起请求，查询id为1的用户
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        User user2 = userMapper2.findUserById(1);
        System.out.println(user2);

        sqlSession2.close();
    }
}
