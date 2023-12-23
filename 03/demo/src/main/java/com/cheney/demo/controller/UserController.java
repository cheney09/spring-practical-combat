package com.cheney.demo.controller;

import com.cheney.demo.dao.UserDao;
import com.cheney.demo.modle.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("queryForList")
    public String queryForList() {
        List<User> users = userDao.getUserByIdViaQueryForList();
        StringBuilder sb = new StringBuilder();
        for (User user : users) {
            sb.append(user).append("</br>");
        }
        return sb.toString();
    }

    @GetMapping("update")
    public String update() {
        int count = userDao.insertUser();
        return "插入了一个id是5的用户，并且年龄是44";
    }

    @GetMapping("query")
    public String query() {
        List<User> users = userDao.getUserByIdViaQuery(5L);
        if (users.isEmpty()) {
            return null;
        }
        return users.getFirst().toString();
    }

    @GetMapping("execute")
    public String execute() {
        userDao.updateUser();
        return "把id是5的用户的年龄修改为55";
    }

    @GetMapping("queryForObject")
    public String queryForObject() {
        User user = userDao.getUserByIdViaQueryForObject(5L);
        if (user == null) {
            return null;
        }
        return user.toString();
    }
}
