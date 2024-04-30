package com.UserBlog.Blog.repository;

import com.UserBlog.Blog.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class PostRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    @Test
    void testFindByID() {
        // Given
        Post post = new Post();
        post.setTitle("Repository Test Post");
        post.setContent("Testing the repository layer.");
        post.setCreatedAt(LocalDateTime.now());
        post = entityManager.persistFlushFind(post);

        // When
        Post foundPost = postRepository.findById(post.getId()).orElse(null);

        // Then
        assertThat(foundPost).isNotNull();
        assertThat(foundPost.getTitle()).isEqualTo("Repository Test Post");
    }
}
