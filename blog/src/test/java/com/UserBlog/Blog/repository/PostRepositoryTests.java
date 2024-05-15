package com.UserBlog.Blog.repository;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PostRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void setUp() {
        entityManager.getEntityManager().createQuery("DELETE FROM Post").executeUpdate();
        entityManager.getEntityManager().createQuery("DELETE FROM User").executeUpdate();

        User user = new User();
        user.setUsername("testAuthor");
        user.setEmail("author@example.com");
        user.setPassword("securepassword");
        entityManager.persistAndFlush(user);

        Post post1 = new Post();
        post1.setTitle("Spring Boot Tips");
        post1.setContent("Useful snippets for Spring Boot.");
        post1.setUser(user);
        entityManager.persistAndFlush(post1);

        Post post2 = new Post();
        post2.setTitle("Hibernate Tips");
        post2.setContent("Tips and tricks for Hibernate.");
        post2.setUser(user);
        entityManager.persistAndFlush(post2);

        // Ensure all entities are saved before tests run
        entityManager.flush();
    }

    @Test
    public void testFindByContentContaining() {
        List<Post> posts = postRepository.findByContentContaining("Hibernate");
        assertThat(posts).hasSize(1).extracting(Post::getTitle).contains("Hibernate Tips");
    }

    @Test
    public void testFindAllPostsOrderByCreatedAtDesc() {
        List<Post> posts = postRepository.findAllPostsOrderByCreatedAtDesc();
        assertThat(posts).hasSize(2);
        assertThat(posts.get(1).getTitle()).isEqualTo("Spring Boot Tips");
        assertThat(posts.get(0).getTitle()).isEqualTo("Hibernate Tips");
    }
}
