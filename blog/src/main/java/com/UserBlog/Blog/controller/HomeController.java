package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.model.Post;
import com.UserBlog.Blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
public class HomeController {

    private final PostService postService;

    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/dashboard")
    public String home(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        }

        // Fetch and add the specific welcome post (e.g., ID = 1)
        Optional<Post> welcomePost = postService.findPostById(1L);
        welcomePost.ifPresent(post -> model.addAttribute("welcomePost", post));
        welcomePost.map(post -> post.getCreatedAt().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")))
                .ifPresent(date -> model.addAttribute("welcomePostDate", date));

        try {
            // Retrieve the latest post
            Post latestPost = postService.findLatestPost();
            if (latestPost != null && latestPost.getCreatedAt() != null) {
                // Format the date
                String formattedDate = latestPost.getCreatedAt().format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
                model.addAttribute("formattedDate", formattedDate);
            } else {
                model.addAttribute("formattedDate", "Date not available");
            }
            model.addAttribute("latestPost", latestPost);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to load data");
        }

        return "dashboard";
    }
}
