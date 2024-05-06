package com.UserBlog.Blog.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    void testSaveUser() {
        User user = new User("username", "email@example.com", "password");
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.save(user);

        verify(passwordEncoder).encode("password");
        verify(userRepository).save(user);
        assertThat(user.getPassword()).isEqualTo("encodedPassword");
    }

    @Test
    void testUserExistsByUsername() {
        when(userRepository.existsByUsername("username")).thenReturn(true);

        boolean exists = userService.existsByUsername("username");

        assertThat(exists).isTrue();
        verify(userRepository).existsByUsername("username");
    }

    @Test
    void testUserExistsByEmail() {
        when(userRepository.existsByEmail("email@example.com")).thenReturn(true);

        boolean exists = userService.existsByEmail("email@example.com");

        assertThat(exists).isTrue();
        verify(userRepository).existsByEmail("email@example.com");
    }
}
