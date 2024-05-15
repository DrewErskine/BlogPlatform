package com.UserBlog.Blog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.UserBlog.Blog.model.Authority;
import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = LoginService.class)
public class LoginServiceTests {

    @Autowired
    private LoginService loginService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        Set<Authority> authorities = Collections.emptySet();
        User testUser = new User(1L, "drew", "drew@example.com", "encodedPassword1", authorities, true);
        
        // Mock password matching
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
        when(passwordEncoder.matches("luf", "encodedPassword1")).thenReturn(true);

        // Setup userRepository to return our test user
        when(userRepository.findByUsername("drew")).thenReturn(Optional.of(testUser));
    }

    @Nested
    class AuthenticateTests {
        @Test
        public void testAuthenticateWithValidCredentials() {
            assertTrue(loginService.authenticate("drew", "luf"));
        }

        @Test
        public void testAuthenticateWithInvalidPassword() {
            assertFalse(loginService.authenticate("drew", "wrongPassword"));
        }
    }

    // Additional tests...
}
