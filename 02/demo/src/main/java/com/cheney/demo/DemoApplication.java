package com.cheney.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.SQLException;

@SpringBootApplication
@RestController
@Slf4j
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate2;

    @RequestMapping("db1")
    public String getUserFromDataSource1() throws SQLException {
        return jdbcTemplate.queryForList("select * from t_user").toString();
    }

    @RequestMapping("db2")
    public String getUserFromDataSource2(){
        return jdbcTemplate2.queryForList("select * from t_user").toString();
    }
}
