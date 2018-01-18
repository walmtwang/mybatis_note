package cn.walmt.mapper;

import cn.walmt.pojo.User;
import cn.walmt.pojo.UserCustom;
import cn.walmt.pojo.UserQueryVo;

import java.util.List;
import java.util.Map;

/**
 * Created by walmt on 2018/1/14.
 */
public interface UserMapper {

    // 用户信息综合查询
    List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;

    // 用户信息综合查询总数
    Integer findUserCount(UserQueryVo userQueryVo) throws Exception;

    // 根据id查询用户信息
    User findUserById(int id) throws Exception;

    // 根据用户名查询用户列表
    List<User> findUserByName(String name) throws Exception;

    // 添加用户信息
    void inserUser(User user) throws Exception;

    // 删除用户信息
    void deleteUser(int id) throws Exception;

    List<UserCustom> findUserListByMap(Map<String, Object> map) throws Exception;

    // 根据id查询用户信息，使用resultMap输出
    User findUserByIdResultMap(int id) throws Exception;

    // 更新用户
    void updateUser(User user) throws Exception;
}
