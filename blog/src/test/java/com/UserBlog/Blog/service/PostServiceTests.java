package com.UserBlog.Blog.service;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class PostServiceTests {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    public PostServiceTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindPostById() {
        Long postId = 1L;
        Post post = new Post();
        post.setId(postId);
        post.setTitle("Sample Title");
        post.setContent("Sample content");
        post.setCreatedAt(java.time.LocalDateTime.now());

        // Mock the behavior of the post repository
        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        // Call the method under test
        Optional<Post> foundPost = postService.findPostById(postId);

        // Validate the results
        assertThat(foundPost.isPresent()).isTrue();
        assertThat(foundPost.get().getTitle()).isEqualTo("Sample Title");
        verify(postRepository).findById(postId);
    }
}
