package cn.walmt.dao;

import cn.walmt.pojo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by walmt on 2018/1/14.
 */
public class UserDaoImpl implements UserDao {

    // 需要向dao实现类注入SqlSessionFactory
    // 这里通过构造方法注入
    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    private SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    @Override
    public User findUserById(int id) throws Exception {
        SqlSession sqlSession = getSqlSession();

        User user = sqlSession.selectOne("test.findUserById", id);

        // 释放资源
        sqlSession.close();

        return user;
    }

    @Override
    public void inserUser(User user) throws Exception {
        SqlSession sqlSession = getSqlSession();

        // 执行插入的操作
        sqlSession.insert("test.insertUser", user);

        // 提交事务
        sqlSession.commit();

        // 释放资源
        sqlSession.close();
    }

    @Override
    public void deleteUser(int id) throws Exception {
        SqlSession sqlSession = getSqlSession();

        // 执行删除的操作
        sqlSession.delete("test.deleteUser", id);

        // 提交事务
        sqlSession.commit();

        // 释放资源
        sqlSession.close();
    }
}
