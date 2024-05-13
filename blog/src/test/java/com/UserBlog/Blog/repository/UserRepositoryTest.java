package com.UserBlog.Blog.repository;

import com.UserBlog.Blog.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql(scripts = "/data.sql")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        Optional<User> user = userRepository.findByUsername("drew");
        assertTrue(user.isPresent());
        assertEquals("drew", user.get().getUsername());
    }
}
