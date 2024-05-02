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
import static org.mockito.Mockito.when;

import java.util.Collections;

@SpringBootTest
@ActiveProfiles("test")
public class BlogApplicationTests {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private PostService postService;

    @MockBean
    private PostRepository postRepository;

    @Test
    void contextLoads() {
        assertThat(context).isNotNull();
    }

    @Test
    void shouldContainServiceBean() {
        assertThat(postService).isNotNull();
    }

    @Test
    void postServiceInteractions() {
        // Given
        when(postRepository.findAll()).thenReturn(Collections.emptyList());
        
        // When
        postService.findAllPosts();
        
        // Then
        verify(postRepository).findAll();
    }
}
