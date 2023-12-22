package com.cheney.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

@Configuration
public class Datasource2Config {

    @Bean
    @ConfigurationProperties("spring.datasource2")
    public DataSource dataSource2(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate2(@Qualifier("dataSource2") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
