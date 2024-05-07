package com.UserBlog.Blog.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.service.UserService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private Model mockModel;
    private BindingResult mockBindingResult;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(userService)).build();
        mockModel = mock(Model.class);
        mockBindingResult = mock(BindingResult.class);
    }

    @Test
    public void testLoginView() throws Exception {
        mockMvc.perform(get("/api/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login.html"));
    }

    @Test
    public void testProcessLogin() throws Exception {
        mockMvc.perform(post("/api/users/login"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void testShowRegistrationForm() throws Exception {
        mockMvc.perform(get("/api/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register.html"));
    }

    @Test
    public void testProcessRegistration() throws Exception {
        User newUser = new User();
        newUser.setUsername("newUser");
        newUser.setEmail("new@example.com");

        given(mockBindingResult.hasErrors()).willReturn(false);
        given(userService.existsByUsername(any())).willReturn(false);
        given(userService.existsByEmail(any())).willReturn(false);

        mockMvc.perform(post("/api/users/register")
                .flashAttr("user", newUser))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/api/users/login"));
    }

    @Test
    public void testProcessRegistrationWithErrors() throws Exception {
        User newUser = new User();
        newUser.setUsername("newUser");
        newUser.setEmail("user@example.com");

        // Create a BindingResult with an error
        Errors errors = new BeanPropertyBindingResult(newUser, "user");
        errors.rejectValue("username", "error.user", "Username already exists");

        // Assume userService methods that would lead to this behavior
        given(userService.existsByUsername(newUser.getUsername())).willReturn(true);
        given(userService.existsByEmail(newUser.getEmail())).willReturn(false);

        mockMvc.perform(post("/api/users/register")
                .flashAttr("user", newUser)
                .flashAttr(BindingResult.MODEL_KEY_PREFIX + "user", errors))
                .andExpect(status().isOk())  // The expected status should be OK if it returns to the form
                .andExpect(view().name("register.html"));
    }
}
