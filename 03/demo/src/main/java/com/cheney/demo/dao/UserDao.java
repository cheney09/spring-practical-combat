package com.cheney.demo.dao;

import com.cheney.demo.modle.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getUserByIdViaQuery(Long id) {
        String sql = "select * from t_user where id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age")
                ), id);
    }

    public User getUserByIdViaQueryForObject(Long id) {
        String sql = "select * from t_user where id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age")
                ), id);
    }

    public List<User> getUserByIdViaQueryForList() {
        String sql = "select * from t_user";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        List<User> users = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            User user = new User();
            user.setId((Integer) row.get("ID"));
            user.setName((String) row.get("NAME"));
            user.setAge((Integer) row.get("AGE"));
            users.add(user);
        }
        return users;
    }

    public int insertUser() {
        String sql = "insert into t_user values (5, 'ccc',44)";
        return jdbcTemplate.update(sql);
    }


    public void updateUser() {
        String sql = "update t_user set age = '55' where id = 5";
        jdbcTemplate.execute(sql);
    }
}
