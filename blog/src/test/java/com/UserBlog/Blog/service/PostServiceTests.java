package com.UserBlog.Blog.service;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PostServiceTests {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    public void testSavePost() {
        Post post = new Post();
        post.setTitle("New Post");
        post.setContent("Content of the new post");

        when(postRepository.save(any(Post.class))).thenReturn(post);

        Post savedPost = postService.savePost(post);

        assertEquals("New Post", savedPost.getTitle());
        assertEquals("Content of the new post", savedPost.getContent());
    }
}