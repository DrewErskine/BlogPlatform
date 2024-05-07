package com.UserBlog.Blog.service;

import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testUpdateUser() {
        // Create a user
        User user = new User();
        user.setId(1L);
        user.setEmail("user@example.com");
        user.setPassword("password123");

        // Mock userRepository's save method to return the user
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Call the updateUser method of userService
        userService.updateUser(user);

        // Verify that userRepository's save method was called with the user
        verify(userRepository).save(user);

        // Assert that the user's id remains unchanged after calling updateUser
        assertThat(user.getId()).isEqualTo(1L);
    }

    @Test
    public void testFindUserByUsername() {
        // Create a user
        User user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        // Mock userRepository's findByUsername method to return the user
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        // Call the findUserByUsername method of userService
        Optional<User> foundUserOptional = userService.findByUsername("testuser");

        // Assert that the user returned by the service is the same as the one we created
        assertThat(foundUserOptional).isPresent();
        assertThat(foundUserOptional.get().getUsername()).isEqualTo("testuser");
    }
}
