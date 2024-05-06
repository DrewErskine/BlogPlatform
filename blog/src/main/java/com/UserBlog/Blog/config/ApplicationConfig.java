package com.UserBlog.Blog.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.jdbc.DataSourceBuilder;
import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
public class ApplicationConfig {

    @Bean(name = "applicationDataSource")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3306/blog?useSSL=false&serverTimezone=UTC")
                .username("root")
                .password("Devindrew42!")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
