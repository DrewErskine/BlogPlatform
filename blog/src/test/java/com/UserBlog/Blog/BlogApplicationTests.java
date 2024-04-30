package com.UserBlog.Blog;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import jakarta.activation.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BlogApplicationTests {

    private ApplicationContext context;

    @Test
    void contextLoads() {
        assertThat(context).isNotNull();
    }

    @Test
    void dataSourceBeanExists() {
        assertThat(context.getBean(DataSource.class)).isNotNull();
    }
}
