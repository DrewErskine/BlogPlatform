package com.UserBlog.Blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean(name = "databaseDataSource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("mysql://localhost:3306/blog?useSSL=false&serverTimezone=UTC")
                .username("root")
                .password("Devindrew42!")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }
}
