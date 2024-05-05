package com.UserBlog.Blog.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser
    void testShowRegistrationForm() throws Exception {
        mockMvc.perform(get("/api/users/register"))
               .andExpect(status().isOk())
               .andExpect(view().name("register"));
    }

    @Test
    @WithMockUser
    void testProcessRegistration() throws Exception {
        mockMvc.perform(post("/api/users/register")
               .contentType(MediaType.APPLICATION_FORM_URLENCODED)
               .param("username", "newuser")
               .param("email", "newuser@example.com")
               .param("password", "password"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/api/users/login"));

        verify(userService).save(any(User.class));
    }
}
