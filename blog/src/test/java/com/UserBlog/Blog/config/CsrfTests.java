package com.UserBlog.Blog.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.test.web.servlet.MockMvc;

import com.UserBlog.Blog.controller.LoginController;
import com.UserBlog.Blog.service.LoginService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
public class CsrfTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @Test
    @WithMockUser
    public void testCsrfTokenIsAddedToModel() throws Exception {
        // Perform the request with automatic CSRF token handling
        mockMvc.perform(get("/loginModelView").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("_csrf"));
    }

    @Test
    @WithMockUser
    public void testRegistrationWithCsrfToken() throws Exception {
        // Generate a CSRF token
        CsrfToken csrfToken = new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", "abc123456");

        // Perform the registration request with the generated CSRF token
        mockMvc.perform(post("/register")
                .param("username", "testuser")
                .param("password", "testpassword")
                .param("email", "test@example.com")
                .header(csrfToken.getHeaderName(), csrfToken.getToken()))
                .andExpect(status().is3xxRedirection()) // Assuming successful registration redirects
                .andExpect(redirectedUrl("/login")); // Assuming redirection URL after successful registration
    }
}
