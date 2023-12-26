package com.cheney.demo.dao;

import com.cheney.demo.modle.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {

    int insertUsers(List<User> users);

    int insertUser(User user);

    @Delete("delete from t_user where id = #{id}")
    int deleteUser(Integer id);

    int updateUser(User user);

    @Select("select * from t_user")
    List<User> selectUsers();

    @Select("select * from t_user where id = #{id}")
    User selectUser(Integer id);
}
