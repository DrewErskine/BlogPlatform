package com.UserBlog.Blog.service;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.repository.PostRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

        private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findByTitleContainingIgnoreCase(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Post> findAllPostsOrderByCreatedAtDesc() {
        return postRepository.findAllPostsOrderByCreatedAtDesc();
    }

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> findPostsByTitle(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Post> findPostsByContent(String keyword) {
        return postRepository.findByContentContaining(keyword);
    }


    public List<Post> findAllPostsSortedByDate() {
        return postRepository.findAllPostsOrderByCreatedAtDesc();
    }

    public Post findLatestPost() {
        List<Post> posts = postRepository.findAllPostsOrderByCreatedAtDesc();
        return posts.isEmpty() ? null : posts.get(0);
    }
}
