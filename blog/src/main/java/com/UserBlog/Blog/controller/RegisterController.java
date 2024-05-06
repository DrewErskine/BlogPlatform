package com.UserBlog.Blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.UserBlog.Blog.model.User;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        // Logic to save the user to the database
        return "redirect:/login"; // Redirect to the login page after successful registration
    }
}
