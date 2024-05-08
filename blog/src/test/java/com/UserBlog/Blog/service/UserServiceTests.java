package com.UserBlog.Blog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.repository.UserRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = UserService.class)
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
    }

    @Test
    public void testRegisterUserWithNewUsername() {
        when(userRepository.existsByUsername("newUser")).thenReturn(false);
        when(userRepository.existsByEmail("newUser@example.com")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        assertTrue(userService.registerUser("newUser", "newPassword", "newUser@example.com"));
        verify(userRepository).save(any(User.class)); // Verify that save is called
    }

    @Test
    public void testRegisterUserWithExistingUsername() {
        when(userRepository.existsByUsername("existingUser")).thenReturn(true);

        assertFalse(userService.registerUser("existingUser", "newPassword", "existingUser@example.com"));
        verify(userRepository, never()).save(any(User.class)); // Ensure save is not called
    }

    @Test
    public void testRegisterUserWithExistingEmail() {
        when(userRepository.existsByEmail("existing@example.com")).thenReturn(true);

        assertFalse(userService.registerUser("newUser", "newPassword", "existing@example.com"));
        verify(userRepository, never()).save(any(User.class)); // Ensure save is not called
    }

    @Test
    public void testErrorHandlingWhenSavingUser() {
        when(userRepository.existsByUsername("newUser")).thenReturn(false);
        when(userRepository.existsByEmail("newUser@example.com")).thenReturn(false);
        doThrow(RuntimeException.class).when(userRepository).save(any(User.class));

        assertThrows(RuntimeException.class, () -> userService.registerUser("newUser", "newPassword", "newUser@example.com"));
    }
}
