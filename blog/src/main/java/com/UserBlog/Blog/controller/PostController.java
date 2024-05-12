package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post/{id}")
    public String viewPost(@PathVariable("id") Long postId, Model model) {
        return postService.findPostById(postId)
                          .map(post -> {
                              model.addAttribute("post", post);
                              return "post";  // name of the Thymeleaf template to render the post
                          })
                          .orElse("error");  // redirect to an error page if the post is not found
    }
}
