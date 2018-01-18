package cn.walmt.service;

import cn.walmt.dao.UserDao;
import cn.walmt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import util.GsonUtil;

import java.util.Date;

/**
 * Created by walmt on 2018/1/17.
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    public String getUserById(Integer id) {

        Assert.notNull(id, "id为空！");

        // 省略其他检测，如id小于0

        User user = userDao.findUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("不存在该用户！");
        }
        return GsonUtil.getSuccessJson(user);
    }

    public void insertUser(String username, String sex, Date birthday, String address) {

        Assert.notNull(username, "用户名为空！");
        // 以下省略各种用户信息的检测

        User user = new User(username, sex, birthday, address);

        userDao.inserUser(user);
        System.out.println(user);
    }
}
