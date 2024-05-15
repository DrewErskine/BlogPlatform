package com.UserBlog.Blog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.UserBlog.Blog.service.LoginService;

public class LoginControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    public void testProcessLoginSuccess() throws Exception {
        when(loginService.authenticate("user", "pass")).thenReturn(true);

        mockMvc.perform(post("/login")
                .param("username", "user")
                .param("password", "pass"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/blogHome"));
    }
}
