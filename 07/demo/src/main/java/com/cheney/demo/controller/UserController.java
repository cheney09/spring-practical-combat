package com.cheney.demo.controller;

import com.cheney.demo.modle.User;
import com.cheney.demo.repository.UserRepository;
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
    private UserRepository userRepository;

    @GetMapping("insertOne")
    public String insertOne() {
        User user = new User(1, "cheney", 11);
        userRepository.save(user);
        return "一个用户信息保存成功";
    }

    @GetMapping("insertMany")
    public String insertMany() {
        List<User> users = new ArrayList<>();
        users.add(new User(2, "aaa", 11));
        users.add(new User(3, "bbb", 11));
        userRepository.saveAll(users);
        return "一组用户信息保存成功";
    }

    @GetMapping("selectAll")
    public String selectAll() {
        List<User> users = userRepository.findAll();
        StringBuilder sb = new StringBuilder();
        for (User user : users) {
            sb.append(user).append("</br>");
        }
        return sb.toString();
    }

    @GetMapping("update")
    public String update() {
        // 修改用户信息
        User user = new User(1, "cheney", 22);
        userRepository.save(user);
        // 查询修改后的结果
        return this.getByName();
    }

    @GetMapping("getByName")
    public String getByName() {
        User user = userRepository.findByName("cheney");
        return user.toString();
    }

    @GetMapping("delete")
    public String delete() {
        // 删除 用户ID是3的用户信息
        userRepository.deleteById(3);
        // 查询删除后的结果
        return this.selectAll();
    }
}
