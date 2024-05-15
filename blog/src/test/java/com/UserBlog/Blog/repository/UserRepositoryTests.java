package com.UserBlog.Blog.repository;

import com.UserBlog.Blog.model.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql(scripts = {"/test-schema.sql", "/test-data.sql"})
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        Optional<User> result = userRepository.findByUsername("drew");
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getEmail()).isEqualTo("Dmerskine19@Gmail.com");
    }
}

