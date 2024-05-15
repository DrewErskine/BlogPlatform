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
        Long postId = 1L; // ID
        String title = "Test Title"; // title
        String content = "Test content"; // content
        LocalDateTime now = LocalDateTime.now(); // Current time
    
        Post post = new Post(); // Post object
        post.setId(postId); // Set post ID
        post.setTitle(title); // Set title
        post.setContent(content); // Set content
        post.setCreatedAt(now); // Set creation date
    
        LocalDateTime expectedCreatedAt = now; 
    
        assertThat(post).extracting(Post::getId, Post::getTitle, Post::getContent, Post::getCreatedAt)
                        .containsExactly(postId, title, content, expectedCreatedAt);
    }
}
