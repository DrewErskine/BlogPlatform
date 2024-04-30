package com.UserBlog.Blog.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

public class PostTests {

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

        assertThat(post.getId()).isEqualTo(postId);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
        assertThat(post.getCreatedAt()).isEqualTo(now);
    }
}
