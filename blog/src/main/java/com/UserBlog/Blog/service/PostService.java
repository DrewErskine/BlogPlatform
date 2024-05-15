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

    /**
     * Saves a given post.
     * @param post the post to save
     * @return the saved post
     */
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    /**
     * Deletes a post by its ID.
     * @param id the ID of the post to delete
     */
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    /**
     * Finds a post by its ID.
     * @param id the ID of the post
     * @return an Optional containing the found post or empty if not found
     */
    public Optional<Post> findPostById(Long id) {
        return postRepository.findById(id);
    }

    /**
     * Finds the most recent post.
     * @return the latest post or null if no posts exist
     */
    public Post findLatestPost() {
        List<Post> posts = postRepository.findAllPostsOrderByCreatedAtDesc();
        return posts.isEmpty() ? null : posts.get(0);
    }

    /**
     * Retrieves all posts.
     * @return a list of all posts
     */
    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    /**
     * Retrieves all posts, ordered by creation date descending.
     * @return a list of all posts
     */
    public List<Post> findAllPostsSortedByDate() {
        return postRepository.findAllPostsOrderByCreatedAtDesc();
    }

    public List<Post> findAllPostsOrderByCreatedAtDesc() {
        return postRepository.findAllPostsOrderByCreatedAtDesc();
    }

}
