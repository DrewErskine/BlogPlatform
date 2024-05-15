package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(HomeController.class)
public class HomeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser
    public void testHome() throws Exception {
        Mockito.when(postService.findPostById(1L)).thenReturn(Optional.of(new Post()));
        Mockito.when(postService.findLatestPost()).thenReturn(new Post());

        mockMvc.perform(get("/dashboard").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"));
    }

    @Test
    @WithMockUser
    public void testHome_NoWelcomePost() throws Exception {
        Mockito.when(postService.findPostById(1L)).thenReturn(Optional.empty());
        Mockito.when(postService.findLatestPost()).thenReturn(new Post());

        mockMvc.perform(get("/dashboard").with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"));
    }
}
