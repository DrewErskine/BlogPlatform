package com.UserBlog.Blog.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.UserBlog.Blog.controller.RegisterController;
import com.UserBlog.Blog.service.UserService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RegisterController.class) // Adjusted to the appropriate controller
public class CsrfTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService; // Keep the service mocked if used by the controller

    @Test
    @WithMockUser
    public void testCsrfTokenIsAddedToModel() throws Exception {
        mockMvc.perform(get("/register").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("_csrf"))
                .andExpect(view().name("register")); // Verify the view name if specific
    }

    @Test
    @WithMockUser
    public void testRegistrationWithCsrfToken() throws Exception {
        mockMvc.perform(post("/register")
                .param("username", "testuser")
                .param("password", "testpassword")
                .param("email", "test@example.com")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/register?success"));
    }
}
