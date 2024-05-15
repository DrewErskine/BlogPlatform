package com.UserBlog.Blog.repository;

import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testFindByUsername_Found() {
        // Arrange
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testemail@example.com");
        user.setPassword("password");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        // Act
        Optional<User> foundUser = userService.findByUsername("testuser");

        // Assert
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("testuser");
        assertThat(foundUser.get().getEmail()).isEqualTo("testemail@example.com");
    }

    @Test
    public void testFindByUsername_NotFound() {
        // Arrange
        when(userRepository.findByUsername("unknownuser")).thenReturn(Optional.empty());

        // Act
        Optional<User> foundUser = userService.findByUsername("unknownuser");

        // Assert
        assertThat(foundUser).isNotPresent();
    }

    @Test
    public void testFindByEmail_Found() {
        // Arrange
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testemail@example.com");
        user.setPassword("password");

        when(userRepository.findByEmail("testemail@example.com")).thenReturn(Optional.of(user));

        // Act
        Optional<User> foundUser = userService.findByEmail("testemail@example.com");

        // Assert
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("testemail@example.com");
    }
}
