package com.UserBlog.Blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.UserBlog.Blog.service.UserService;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@RequestParam String username, @RequestParam String password, @RequestParam String email, Model model) {
        boolean registrationSuccess = userService.registerUser(username, password, email);
        if (registrationSuccess) {
            return "redirect:/login";  // Redirect after successful registration
        } else {
            model.addAttribute("registrationError", "Username or email already exists.");
            return "register";  // Stay on the registration page if not successful
        }
    }
}
