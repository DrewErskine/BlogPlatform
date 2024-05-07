package com.UserBlog.Blog.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.UserBlog.Blog.model.Authority;
import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.service.LoginService;
import com.UserBlog.Blog.service.UserService;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private LoginService loginService;

    @Test
    public void login_ShouldReturnLoginView() throws Exception {
        mockMvc.perform(get("/api/user/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void processLogin_ShouldRedirectToHome() throws Exception {
        // Arrange
        String username = "user";
        String password = "password";
        when(loginService.authenticate(username, password)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(post("/api/user/login")
                .param("username", username)
                .param("password", password))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"));  // Ensure the redirection URL is correct based on your controller logic
    }

    @Test
    public void processLogin_ShouldReturnLoginViewOnError() throws Exception {
        // Arrange
        String username = "user";
        String password = "wrongpassword";
        when(loginService.authenticate(username, password)).thenReturn(false);

        // Act & Assert
        mockMvc.perform(post("/api/user/login")
                .param("username", username)
                .param("password", password))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("loginError"));
    }

    @Test
    public void testShowRegistrationForm() throws Exception {
        mockMvc.perform(get("/api/user/register"))
               .andExpect(status().isOk())
               .andExpect(view().name("register"));
    }

    @Test
    public void processRegistration_ShouldRedirectOnSuccess() throws Exception {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(new Authority("ROLE_USER"));
        User newUser = new User("newUser", "new@example.com", "password123", authorities);

        given(userService.existsByUsername("newUser")).willReturn(false);
        given(userService.existsByEmail("new@example.com")).willReturn(false);

        mockMvc.perform(post("/api/user/register")
                .flashAttr("user", newUser))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        verify(userService, times(1)).save(any(User.class));
    }

    @Test
    public void processRegistration_ShouldReturnRegisterViewOnError() throws Exception {
        Set<Authority> authorities = new HashSet<>();
        authorities.add(new Authority("ROLE_USER"));
        User newUser = new User("newUser", "user@example.com", "password123", authorities);

        given(userService.existsByUsername("newUser")).willReturn(true);

        mockMvc.perform(post("/api/user/register")
                .flashAttr("user", newUser))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));

        verify(userService, never()).save(any(User.class));
    }
}
