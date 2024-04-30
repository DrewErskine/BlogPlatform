package com.UserBlog.Blog.repository;

import com.UserBlog.Blog.model.Post;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PostRepositoryTests {


    private TestEntityManager entityManager;


    private PostRepository postRepository;

    @Test
    void testFindByID() {
        // Given
        Post post = new Post();
        post.setTitle("Repository Test Post");
        post.setContent("Testing the repository layer.");
        post = entityManager.persistFlushFind(post);  // Persist and immediately find to flush to DB and then read.

        // When
        Post foundPost = postRepository.findById(post.getId()).orElse(null);

        // Then
        assertThat(foundPost).isNotNull();
        assertThat(foundPost.getTitle()).isEqualTo("Repository Test Post");
    }
}
