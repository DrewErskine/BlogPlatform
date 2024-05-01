package com.UserBlog.Blog.repository;

import com.UserBlog.Blog.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
public class PostRepositoryTests {

    @MockBean
    private PostRepository postRepository;

    @Test
    public void testFindByTitleContainingIgnoreCase() {
        // Create some test data
        List<Post> posts = new ArrayList<>();
        posts.add(createTestPost("Title 1", "Content 1"));
        posts.add(createTestPost("Another Title", "Content 2"));

        // Mock the behavior of postRepository
        when(postRepository.findByTitleContainingIgnoreCase("title")).thenReturn(posts);

        // Perform the repository method
        List<Post> foundPosts = postRepository.findByTitleContainingIgnoreCase("title");

        // Assertions
        assertEquals(2, foundPosts.size());
    }

    @Test
    public void testFindByContentContaining() {
        // Create some test data
        List<Post> posts = new ArrayList<>();
        posts.add(createTestPost("Title 1", "Some content with keyword in it"));
        posts.add(createTestPost("Another Title", "Content without keyword"));

        // Mock the behavior of postRepository
        when(postRepository.findByContentContaining("keyword")).thenReturn(posts);

        // Perform the repository method
        List<Post> foundPosts = postRepository.findByContentContaining("keyword");

        // Assertions
        assertEquals(2, foundPosts.size());
        assertEquals("Some content with keyword in it", foundPosts.get(0).getContent());
    }

    @Test
    public void testFindAllPostsOrderByCreatedAtDesc() {
        // Create some test data with different creation dates
        List<Post> posts = new ArrayList<>();
        posts.add(createTestPost("Title 1", "Content 1"));
        Post post2 = createTestPost("Another Title", "Content 2");
        post2.setCreatedAt(LocalDateTime.now().plusSeconds(10)); // Introducing some delay
        posts.add(post2);

        // Mock the behavior of postRepository
        when(postRepository.findAllPostsOrderByCreatedAtDesc()).thenReturn(posts);

        // Perform the repository method
        List<Post> foundPosts = postRepository.findAllPostsOrderByCreatedAtDesc();

        // Assertions
        assertEquals(2, foundPosts.size());
        assertEquals("Title 1", foundPosts.get(0).getTitle());
    }

    private Post createTestPost(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCreatedAt(LocalDateTime.now());
        // Set other properties as needed
        return post;
    }
}
