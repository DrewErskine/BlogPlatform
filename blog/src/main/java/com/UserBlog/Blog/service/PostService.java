package com.UserBlog.Blog.service;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Fetch all posts
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    // Find a post by its ID
    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    // Save a new or existing post
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    // Delete a post by ID
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    // Search for posts containing certain text in the title
    public List<Post> findPostsByTitle(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title);
    }

    // Find posts containing a specific keyword in their content
    public List<Post> findPostsByContent(String keyword) {
        return postRepository.findByContentContaining(keyword);
    }

    // Get all posts ordered by creation date descending
    public List<Post> findAllPostsSortedByDate() {
        return postRepository.findAllPostsOrderByCreatedAtDesc();
    }

    // (Optional) Find posts by author, if you have an author relationship
    public List<Post> findPostsByAuthorId(Long authorId) {
        return postRepository.findByAuthorId(authorId);
    }
}
