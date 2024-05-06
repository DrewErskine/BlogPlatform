package com.UserBlog.Blog.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

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

    @Test
    void testRegisterNewUserAccount() {
        User newUser = new User("newUser", "newuser@example.com", "newpassword");
        when(userRepository.existsByUsername("newUser")).thenReturn(false);
        when(passwordEncoder.encode("newpassword")).thenReturn("encodedNewPassword");
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        boolean result = userService.registerNewUserAccount(newUser);

        assertThat(result).isTrue();
        verify(userRepository).save(newUser);
        assertThat(newUser.getPassword()).isEqualTo("encodedNewPassword");
    }

    @Test
    void testAuthenticateUser() {
        User existingUser = new User("existingUser", "existinguser@example.com", "password");
        when(userRepository.findByUsername("existingUser")).thenReturn(java.util.Optional.of(existingUser));
        when(passwordEncoder.matches("password", existingUser.getPassword())).thenReturn(true);

        boolean result = userService.authenticate("existingUser", "password");

        assertThat(result).isTrue();
    }
}
