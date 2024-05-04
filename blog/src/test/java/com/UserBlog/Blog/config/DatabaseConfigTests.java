package com.UserBlog.Blog.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class DatabaseConfigTests {

    @Autowired
    @Qualifier("applicationDataSource")
    private DataSource dataSource;

    @Test
    void dataSourcePropertiesAndQueryExecution() throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            assertThat(conn).isNotNull();
            assertThat(conn.getMetaData().getURL()).contains("jdbc:mysql://localhost:3306/blog?useSSL=false&serverTimezone=UTC");
            assertThat(conn.getMetaData().getUserName()).containsIgnoringCase("root");
        }
    }
}
