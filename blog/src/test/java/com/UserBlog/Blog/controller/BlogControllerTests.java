package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.service.PostService;
import com.UserBlog.Blog.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class BlogControllerTests {

    private MockMvc mockMvc;

    @Mock
    private PostService postService;

    @Mock
    private UserService userService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private BlogController blogController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(blogController).build();
    }

    @Test
    public void testShowBlogPosts() throws Exception {
        when(postService.findAllPosts()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/blog"))
                .andExpect(status().isOk())
                .andExpect(view().name("blogList"))
                .andExpect(model().attributeExists("posts"));
    }

    @Test
    public void testShowBlogForm() throws Exception {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("testUser");
        User user = new User();
        when(userService.findByUsername("testUser")).thenReturn(Optional.of(user));

        mockMvc.perform(get("/blog/form"))
                .andExpect(status().isOk())
                .andExpect(view().name("blogForm"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("post"));

        verify(userService, times(1)).findByUsername("testUser");
    }

    @Test
    public void testShowBlogForm_UserNotFound() throws Exception {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("testUser");
        when(userService.findByUsername("testUser")).thenReturn(Optional.empty());

        mockMvc.perform(get("/blog/forms"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testViewPost() throws Exception {
        Post post = new Post();
        when(postService.findPostById(1L)).thenReturn(Optional.of(post));

        mockMvc.perform(get("/blog/post/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("post"))
                .andExpect(model().attributeExists("post"));

        verify(postService, times(1)).findPostById(1L);
    }
}
