package com.cheney.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

    @Autowired
    private DataSource dataSource;
    @RequestMapping("info")
    public String info() throws SQLException {
        StringBuilder sb = new StringBuilder();
        Connection connection = dataSource.getConnection();
        sb.append("Connection Info:</br>");
        sb.append(connection.toString());
        connection.close();
        return sb.toString();
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @RequestMapping("user")
    public String getUser() throws SQLException {
        return jdbcTemplate.queryForList("select * from t_user").toString();
    }
}
