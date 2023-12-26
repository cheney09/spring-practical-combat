package com.cheney.demo.controller;

import com.cheney.demo.dao.UserDao;
import com.cheney.demo.modle.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("insertUser")
    public String insertUser() {
        User user = User.builder().id(1).name("cheney").age(11).build();
        userDao.insertUser(user);
        return "登陆了一条用户信息:</br>" + user.toString();
    }

    @GetMapping("insertUsers")
    public String insertUsers() {
        List<User> users = new ArrayList<>();
        users.add(User.builder().id(2).name("aaa").age(12).build());
        users.add(User.builder().id(3).name("bbb").age(13).build());
        userDao.insertUsers(users);
        StringBuilder sb = new StringBuilder();
        sb.append("登陆了两条用户信息:</br>");
        for (User user : users) {
            sb.append(user).append("</br>");
        }
        return sb.toString();
    }

    @GetMapping("updateUser")
    public String updateUser() {
        User user = User.builder().id(1).name("cheney").age(22).build();
        userDao.updateUser(user);
        return "修改了用户ID是1的用户信息:</br>" + user.toString();
    }

    @GetMapping("selectUser")
    public String selectUser() {
        User user = userDao.selectUser(1);
        return "查询用户ID是1的用户信息:</br>" + user.toString();
    }

    @GetMapping("deleteUser")
    public String deleteUser() {
        userDao.deleteUser(3);
        return "删除了用户ID是3的用户信息";
    }

    @GetMapping("selectUsers")
    public String selectUsers() {
        List<User> users = userDao.selectUsers();
        StringBuilder sb = new StringBuilder();
        sb.append("查询所有数据库中的用户信息:</br>");
        for (User user : users) {
            sb.append(user).append("</br>");
        }
        return sb.toString();
    }
}
