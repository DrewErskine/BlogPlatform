package com.UserBlog.Blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";  // Corresponds to src/main/webapp/WEB-INF/views/home.jsp
    }
}
