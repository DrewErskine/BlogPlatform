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
        Long postId = 1L; // Simulated post ID
        String title = "Test Title"; // Simulated title
        String content = "Test content"; // Simulated content
        LocalDateTime now = LocalDateTime.now(); // Current time for createdAt

        Post post = new Post(); // Instantiate Post object
        post.setId(postId); // Set post ID
        post.setTitle(title); // Set title
        post.setContent(content); // Set content
        post.setCreatedAt(now); // Set creation date

        assertThat(post).extracting(Post::getId, Post::getTitle, Post::getContent, Post::getCreatedAt)
        .containsExactly(postId, title, content, now);

    }
}
