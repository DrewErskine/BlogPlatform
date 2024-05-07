package com.UserBlog.Blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

    @GetMapping("/blog")
    public String showBlogForm() {
        return "blogForm";
    }
}