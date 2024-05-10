package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.*;
import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(PostApiController.class)
public class PostControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new PostApiController(postService)).build();
    }

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

        given(postService.savePost(any(Post.class))).willReturn(newPost);

        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newPost)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Post"));
    }
}
