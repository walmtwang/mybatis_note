package cn.walmt.controller;

import cn.walmt.entity.User;
import cn.walmt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import util.GsonUtil;

import java.util.Date;

/**
 * Created by walmt on 2018/1/17.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }

    @ResponseBody
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    public String getUserById(Integer id) {
        return userService.getUserById(id);
    }

    @ResponseBody
    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    public String insertUser(String username,
                             String sex,
                             @DateTimeFormat(pattern = User.DEFAULT_DATE_FORRMATTED) Date birthday,
                             String address) {
        userService.insertUser(username, sex, birthday, address);
        return GsonUtil.getSuccessJson();
    }


}
