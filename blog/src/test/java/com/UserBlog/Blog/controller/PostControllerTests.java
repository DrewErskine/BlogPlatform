package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(PostController.class)
public class PostControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    // Tests will be here
    @Test
    public void testGetAllPosts() throws Exception {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Test Post");
        post.setContent("Test content");

        given(postService.findAllPosts()).willReturn(Arrays.asList(post));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/posts")
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

        mockMvc.perform(MockMvcRequestBuilders.get("/api/posts/" + postId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Specific Post"));
    }

    @Test
    void shouldReturnNotFoundForMissingPost() throws Exception {
        Long postId = 2L;
        given(postService.findPostById(postId)).willReturn(Optional.empty());

        mockMvc.perform(get("/api/posts/{id}", postId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreatePost() throws Exception {
        Post newPost = new Post();
        newPost.setTitle("New Post");
        newPost.setContent("Content of the new post");

        Post returnedPost = new Post();
        returnedPost.setId(3L);
        returnedPost.setTitle(newPost.getTitle());
        returnedPost.setContent(newPost.getContent());

        given(postService.savePost(any(Post.class))).willReturn(returnedPost);

        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(newPost)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(3L))
            .andExpect(jsonPath("$.title").value("New Post"));
    }

}