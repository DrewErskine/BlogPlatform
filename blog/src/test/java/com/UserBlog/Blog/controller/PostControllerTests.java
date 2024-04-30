package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(PostController.class)
public class PostControllerTests {

    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    public void testGetAllPosts() throws Exception {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Test Post");
        post.setContent("Test content");

        given(postService.findAllPosts()).willReturn(Arrays.asList(post));

        mockMvc.perform(get("/api/posts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Post"));
    }

    @Test
    public void testGetPostById() throws Exception {
        Long postId = 1L;
        Post post = new Post();
        post.setId(postId);
        post.setTitle("Specific Post");

        given(postService.findPostById(postId)).willReturn(Optional.of(post));

        mockMvc.perform(get("/api/posts/{id}", postId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Specific Post"));
    }

    @Test
    public void testCreatePost() throws Exception {
        Post newPost = new Post();
        newPost.setTitle("New Post");
        newPost.setContent("Content of the new post");

        given(postService.savePost(any())).willReturn(newPost);

        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newPost)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Post"));
    }
}
