package com.UserBlog.Blog.service;

import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.getContext().setAuthentication(
            new UsernamePasswordAuthenticationToken(userDetails, null)
        );
    }

    @Test
    public void testGetCurrentUser() {
        User user = new User();
        when(userDetails.getUsername()).thenReturn("testUser");
        when(userRepository.findByUsername("testUser")).thenReturn(Optional.of(user));

        Optional<User> currentUser = userService.getCurrentUser();
        assertTrue(currentUser.isPresent());
        assertEquals(user, currentUser.get());
    }

    @Test
    public void testRegisterUser_Success() {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(new User());

        User registeredUser = userService.registerUser("newUser", "password", "email@example.com");
        assertNotNull(registeredUser);
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testRegisterUser_UsernameExists() {
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser("existingUser", "password", "email@example.com");
        });

        assertEquals("Username already exists: existingUser", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testRegisterUser_EmailExists() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser("newUser", "password", "existing@example.com");
        });

        assertEquals("Email already exists: existing@example.com", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }
}
