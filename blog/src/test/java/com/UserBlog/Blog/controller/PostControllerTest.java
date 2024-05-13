package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    private Post post1;
    private Post post2;

    @BeforeEach
    void setUp() {
        post1 = new Post();
        post1.setId(1L);
        post1.setTitle("First Post");
        post1.setContent("Content of the first post.");

        post2 = new Post();
        post2.setId(2L);
        post2.setTitle("Second Post");
        post2.setContent("Content of the second post.");

        when(postService.findAllPosts()).thenReturn(Arrays.asList(post1, post2));
        when(postService.findPostById(anyLong())).thenReturn(Optional.of(post1));
        when(postService.savePost(any(Post.class))).thenReturn(post1);
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetAllPosts() throws Exception {
        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].title").value("First Post"))
                .andExpect(jsonPath("$[1].title").value("Second Post"));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testCreatePost() throws Exception {
        String postJson = "{\"title\":\"New Post\",\"content\":\"This is a new post.\"}";

        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(postJson))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("First Post"))
                .andExpect(jsonPath("$.content").value("Content of the first post."));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testDeletePost() throws Exception {
        doNothing().when(postService).deletePost(anyLong());

        mockMvc.perform(delete("/api/posts/{id}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testDeletePost_NotFound() throws Exception {
        when(postService.findPostById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/posts/{id}", 1L))
                .andExpect(status().isNotFound());
    }
}
