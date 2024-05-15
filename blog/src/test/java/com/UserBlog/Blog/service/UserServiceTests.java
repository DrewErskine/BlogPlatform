package com.UserBlog.Blog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.repository.UserRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = UserService.class)
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setUsername("testUser");
        user.setEmail("testemail@example.com");
        user.setPassword("password");
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));
        when(userRepository.findByEmail("testemail@example.com")).thenReturn(Optional.of(user));
    }

    @Test
    public void testFindUserByUsername() {
        User user = userService.findByUsername("testUser").orElse(null);
        assertNotNull(user, "User should not be null");
        assertEquals("testUser", user.getUsername(), "Username should match");
    }

    @Test
    public void testFindByUsername_Found() {
        Optional<User> foundUser = userService.findByUsername("testUser");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("testUser");
        assertThat(foundUser.get().getEmail()).isEqualTo("testemail@example.com");
    }

    @Test
    public void testFindByUsername_NotFound() {
        when(userRepository.findByUsername("unknownuser")).thenReturn(Optional.empty());
        Optional<User> foundUser = userService.findByUsername("unknownuser");
        assertThat(foundUser).isNotPresent();
    }

    @Test
    public void testFindByEmail_Found() {
        Optional<User> foundUser = userService.findByEmail("testemail@example.com");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("testemail@example.com");
    }

    @Test
    public void testRegisterUserWithExistingUsername() {
        when(userRepository.existsByUsername("existingUser")).thenReturn(true);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser("existingUser", "newPassword", "existingUser@example.com");
        });
        assertTrue(exception.getMessage().contains("Username already exists"));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testRegisterUserWithExistingEmail() {
        when(userRepository.existsByEmail("existing@example.com")).thenReturn(true);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser("newUser", "newPassword", "existing@example.com");
        });
        assertTrue(exception.getMessage().contains("Email already exists"));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testErrorHandlingWhenSavingUser() {
        when(userRepository.existsByUsername("newUser")).thenReturn(false);
        when(userRepository.existsByEmail("newUser@example.com")).thenReturn(false);
        doThrow(RuntimeException.class).when(userRepository).save(any(User.class));
        assertThrows(RuntimeException.class, () -> userService.registerUser("newUser", "newPassword", "newUser@example.com"));
    }
}
