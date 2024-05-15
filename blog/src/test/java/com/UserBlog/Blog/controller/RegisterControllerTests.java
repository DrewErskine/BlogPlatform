package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RegisterControllerTests {

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
    public void whenRegisterSuccess_thenReturnRedirect() throws Exception {
        mockMvc.perform(post("/register")
                .param("username", "new_user")
                .param("email", "new_user@example.com")
                .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/blogHome"));
    }

    @Test
    public void whenRegisterFailure_dueToExistingUser_thenReturnFormView() throws Exception {
        doThrow(new IllegalArgumentException("User already exists")).when(userService).registerUser(anyString(), anyString(), anyString());

        mockMvc.perform(post("/register")
                .param("username", "existing_user")
                .param("email", "user@example.com")
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name("register-form"))
                .andExpect(model().attributeExists("registrationError"))
                .andExpect(model().attribute("registrationError", "User already exists"));
    }

    @Test
    public void whenRegisterFailure_dueToOtherError_thenReturnFormView() throws Exception {
        doThrow(new RuntimeException("Unexpected error")).when(userService).registerUser(anyString(), anyString(), anyString());

        mockMvc.perform(post("/register")
                .param("username", "new_user")
                .param("email", "new_user@example.com")
                .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(view().name("register-form"))
                .andExpect(model().attributeExists("registrationError"))
                .andExpect(model().attribute("registrationError", "An error occurred during registration."));
    }
}
