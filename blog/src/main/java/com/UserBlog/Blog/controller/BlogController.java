package com.UserBlog.Blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.service.PostService;
import java.util.List;  // Example additional import if you're listing posts in blogHomePage

@Controller
public class BlogController {
    
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
    private final PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/blog")
    public String showBlogPosts(Model model) {
        List<Post> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "blog";
    }    

    @GetMapping("/blogForm")
    public String showBlogForm(Model model) {
        Post newPost = new Post();
        logger.info("Creating a new post: {}", newPost);
        model.addAttribute("post", newPost);
        return "blogForm";
    }    

    @GetMapping("/post/{id}")
    public String viewPost(@PathVariable("id") Long postId, Model model) {
        return postService.findPostById(postId)
                          .map(post -> {
                              model.addAttribute("post", post);
                              logger.info("Displaying post with ID: {}", postId);
                              return "post";  
                          })
                          .orElse("error");  
    }
}
