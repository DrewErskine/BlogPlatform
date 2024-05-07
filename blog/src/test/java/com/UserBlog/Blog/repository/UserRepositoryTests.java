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
public class UserRepositoryTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testFindByUsername() {
        User user = new User(null, "testuser", "testemail@example.com", "password", null);

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        userService = new UserService(userRepository);

        Optional<User> foundUser = userService.findByUsername("testuser");

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("testuser");
        assertThat(foundUser.get().getEmail()).isEqualTo("testemail@example.com");
        assertThat(foundUser.get().getPassword()).isEqualTo("password");
    }

    @Test
    public void testFindByEmail() {
        User user = new User(null, "testuser", "testemail@example.com", "password", null);

        when(userRepository.findByEmail("testemail@example.com")).thenReturn(Optional.of(user));

        userService = new UserService(userRepository);

        Optional<User> foundUser = userService.findByEmail("testemail@example.com");

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("testuser");
        assertThat(foundUser.get().getEmail()).isEqualTo("testemail@example.com");
        assertThat(foundUser.get().getPassword()).isEqualTo("password");
    }

    // Add more test cases for other methods as needed
}
