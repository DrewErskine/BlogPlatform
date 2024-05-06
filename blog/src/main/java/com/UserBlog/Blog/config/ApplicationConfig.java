package com.UserBlog.Blog.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.jdbc.DataSourceBuilder;
import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(basePackages = "com.UserBlog.Blog.repository")  // JPA repository
@EnableTransactionManagement
public class ApplicationConfig {

    @Bean(name = "DataSource")
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
