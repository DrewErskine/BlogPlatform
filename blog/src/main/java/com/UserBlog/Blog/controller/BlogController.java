package com.UserBlog.Blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
    
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    @GetMapping("/blog")
    public String showBlogForm() {
        logger.info("Showing blog form.");
        return "blogForm";
    }

    @GetMapping("/blogHome")
    public String blogHomePage(Model model) {
        logger.info("Accessing blog home page.");
        return "blogHome";
    }
}
