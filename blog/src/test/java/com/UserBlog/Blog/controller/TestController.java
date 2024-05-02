package com.UserBlog.Blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/testError")
    public void throwError() {
        throw new RuntimeException("This is a test exception");
    }
}
