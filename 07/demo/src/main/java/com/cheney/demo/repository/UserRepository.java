package com.cheney.demo.repository;

import com.cheney.demo.modle.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    // 自定义一个通过名字查询用户信息的方法
    User findByName(String name);
}
