package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.service.PostService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@Controller
@RequestMapping("/post")
public class PostViewController {
    private PostService postService;

    public PostViewController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/view")
    public String viewPost(@RequestParam("id") Long postId, Model model) {
        Post post = postService.findPostById(postId)
                               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));
        model.addAttribute("post", post);
        return "post";
    }
}