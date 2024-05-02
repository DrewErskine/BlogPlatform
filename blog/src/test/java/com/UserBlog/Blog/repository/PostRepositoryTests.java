package com.UserBlog.Blog.repository;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.service.PostService; // Import PostService
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PostRepositoryTests {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService; // Use PostService here

    @Test
    public void testFindByTitleContainingIgnoreCase() {
        // Given
        Post post1 = new Post("Title 1", "Content 1");
        Post post2 = new Post("Title 2", "Content 2");

        // Mock behavior of postRepository
        when(postRepository.findByTitleContainingIgnoreCase("title")).thenReturn(Arrays.asList(post1, post2));

        // Inject mocked postRepository into postService
        postService = new PostService(postRepository); // Assuming PostServiceImpl is your service implementation

        // When
        List<Post> foundPosts = postService.findByTitleContainingIgnoreCase("title");

        // Then
        assertThat(foundPosts).hasSize(2);
        assertThat(foundPosts).containsExactlyInAnyOrder(post1, post2);
    }
    

    @Test
    public void testFindAllPostsOrderByCreatedAtDesc() {
        // Given
        Post post1 = new Post("Title 1", "Content 1");
        Post post2 = new Post("Title 2", "Content 2");
        Post post3 = new Post("Title 3", "Content 3");

        when(postRepository.findAllPostsOrderByCreatedAtDesc()).thenReturn(Arrays.asList(post3, post2, post1));

        // When
        List<Post> foundPosts = postService.findAllPostsOrderByCreatedAtDesc();

        // Then
        assertThat(foundPosts).hasSize(3);
        assertThat(foundPosts.get(0)).isEqualTo(post3);
        assertThat(foundPosts.get(1)).isEqualTo(post2);
        assertThat(foundPosts.get(2)).isEqualTo(post1);
    }
}
