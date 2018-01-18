package cn.walmt.dao;

import cn.walmt.pojo.User;

/**
 * Created by walmt on 2018/1/14.
 */
public interface UserDao {

    // 根据id查询用户信息
    public User findUserById(int id) throws Exception;

    // 添加用户信息
    public void inserUser(User user) throws Exception;

    // 删除用户信息
    public void deleteUser(int id) throws Exception;
}
