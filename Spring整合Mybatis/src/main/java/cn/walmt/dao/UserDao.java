package cn.walmt.dao;

import cn.walmt.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

/**
 * Created by walmt on 2018/1/14.
 */
public interface UserDao {

    // 根据id查询用户信息
    @Select("SELECT * FROM USER u WHERE u.username LIKE '%${value}%'")
    public User findUserById(int id);

    // 添加用户信息
    @Insert("insert into user(username, birthday, sex, address) values ( #{username}, #{birthday}, #{sex}, #{address})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public void inserUser(User user);
}
