package com.UserBlog.Blog.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

public class PostTests {

    /**
     * Tests the setters and getters of the Post class to ensure they operate as expected.
     */
    @DisplayName("Test Post Model Setters and Getters")
    @Test
    void testPostSettersAndGetters() {
        Long postId = 1L; 
        String title = "Test Title"; 
        String content = "Test content"; 
        LocalDateTime now = LocalDateTime.now(); 
    
        Post post = new Post();
        post.setId(postId); 
        post.setTitle(title);
        post.setContent(content); 
        post.setCreatedAt(now);
    
        LocalDateTime expectedCreatedAt = now; 
    
        assertThat(post).extracting(Post::getId, Post::getTitle, Post::getContent, Post::getCreatedAt)
                        .containsExactly(postId, title, content, expectedCreatedAt);
    }
}
