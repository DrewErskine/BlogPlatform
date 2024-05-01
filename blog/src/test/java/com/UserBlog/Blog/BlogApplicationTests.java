package com.UserBlog.Blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

import com.UserBlog.Blog.repository.PostRepository;
import com.UserBlog.Blog.service.PostService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
public class BlogApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Autowired // Directly autowire the PostService
    private PostService postService;

    @MockBean
    private PostRepository postRepository;

    @Test
    void contextLoads() {
        assertThat(context).isNotNull();
    }

    @Test
    void shouldContainServiceBean() {
        assertThat(postService).as("Check that PostService is correctly instantiated").isNotNull();
    }

    @Test
    void postServiceInteractions() {
        postService.findAllPosts();
        verify(postRepository).findAll(); // Verify that findAll is called when findAllPosts is invoked
    }
}
