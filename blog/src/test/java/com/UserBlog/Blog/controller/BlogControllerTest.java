package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BlogController.class)
class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    void testShowBlogForm() throws Exception {
        mockMvc.perform(get("/blog/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("blogForm"));
    }

    @Test
    @WithMockUser(username = "testUser", roles = {"USER"})
    void testBlogHomePage() throws Exception {
        Mockito.when(postService.findAllPosts()).thenReturn(new ArrayList<>()); // Mock the service response

        mockMvc.perform(get("/blog"))
                .andExpect(status().isOk())
                .andExpect(view().name("blog"))
                .andExpect(model().attributeExists("posts"));
    }
}
