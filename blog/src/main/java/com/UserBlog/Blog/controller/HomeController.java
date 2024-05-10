package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @GetMapping("/dashboard")
    public String home(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }
        // Retrieve the latest post
        Post latestPost = postService.findLatestPost();
        model.addAttribute("latestPost", latestPost);
        return "dashboard";
    }
}
