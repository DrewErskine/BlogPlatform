package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private final PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/form")
    public String showBlogForm(Model model) {
        model.addAttribute("post", new Post());
        return "blogForm";
    }

    @GetMapping
    public String showBlogPosts(Model model) {
        model.addAttribute("posts", postService.findAllPosts());
        return "blog";
    }
}
