package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RegisterControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private RegisterController registerController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
    }

    @Test
    void testShowRegistrationForm() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("registerForm")) // Ensure this matches the view name returned by the controller
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testRegisterUser() throws Exception {
        User user = new User();
        user.setId(1L);
        when(userService.registerUser(anyString(), anyString(), anyString())).thenReturn(user);

        mockMvc.perform(post("/register")
                .param("username", "newUser")
                .param("password", "password")
                .param("email", "email@example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register?success"));
    }
}
