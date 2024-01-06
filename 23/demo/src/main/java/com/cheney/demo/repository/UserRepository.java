package com.cheney.demo.repository;

import com.cheney.demo.modle.User;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    // 自定义一个通过名字查询用户信息的方法
    User findByName(String name);

    // 分页和排序
    Page<User> findAll(@NonNull Pageable pageable);
}
