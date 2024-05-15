package com.UserBlog.Blog.repository;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PostRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    public void setUp() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("password");
        user.setEmail("test@example.com");
        user.setEnabled(true); // Ensure enabled is set to true
        entityManager.persistAndFlush(user);

        Post post1 = new Post();
        post1.setTitle("First Post");
        post1.setContent("Content of the first post");
        post1.setUser(user);
        entityManager.persistAndFlush(post1);

        Post post2 = new Post();
        post2.setTitle("Second Post");
        post2.setContent("Content of the second post");
        post2.setUser(user);
        entityManager.persistAndFlush(post2);
    }

    @Test
    public void testFindByContentContaining() {
        List<Post> posts = postRepository.findByContentContaining("first");
        assertThat(posts).hasSize(1).extracting(Post::getTitle).contains("First Post");
    }

    @Test
    public void testFindAllPostsOrderByCreatedAtDesc() {
        List<Post> posts = postRepository.findAllPostsOrderByCreatedAtDesc();
        assertThat(posts).hasSize(2);
        assertThat(posts.get(0).getTitle()).isEqualTo("Second Post");
        assertThat(posts.get(1).getTitle()).isEqualTo("First Post");
    }
}
