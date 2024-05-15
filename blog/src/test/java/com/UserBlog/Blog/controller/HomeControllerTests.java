package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HomeControllerTests {

    private MockMvc mockMvc;

    @Mock
    private PostService postService;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    public void testHome() throws Exception {
        Post welcomePost = new Post();
        welcomePost.setCreatedAt(LocalDateTime.now());
        when(postService.findPostById(1L)).thenReturn(Optional.of(welcomePost));

        Post latestPost = new Post();
        latestPost.setCreatedAt(LocalDateTime.now());
        when(postService.findLatestPost()).thenReturn(latestPost);

        mockMvc.perform(get("/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"))
                .andExpect(model().attributeExists("welcomePost"))
                .andExpect(model().attributeExists("latestPost"))
                .andExpect(model().attributeExists("formattedDate"));

        verify(postService, times(1)).findPostById(1L);
        verify(postService, times(1)).findLatestPost();
    }

    @Test
    public void testHome_NoWelcomePost() throws Exception {
        when(postService.findPostById(1L)).thenReturn(Optional.empty());
        when(postService.findLatestPost()).thenReturn(null);

        mockMvc.perform(get("/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"))
                .andExpect(model().attributeDoesNotExist("welcomePost"))
                .andExpect(model().attributeExists("formattedDate"))
                .andExpect(model().attributeDoesNotExist("latestPost"));

        verify(postService, times(1)).findPostById(1L);
        verify(postService, times(1)).findLatestPost();
    }
}
