package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    private final LoginService loginService;

    // Constructor injection for the LoginService
    public RegisterController(LoginService loginService) {
        this.loginService = loginService;
    }

    // Mapping to display the registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Mapping to handle form submission and process registration
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("user") User user) {
        // Call the service method to register the user
        boolean registrationSuccess = loginService.registerUser(user.getUsername(), user.getPassword());

        // Redirect to appropriate page based on registration success
        if (registrationSuccess) {
            return "redirect:/login"; 
        } else {
            return "redirect:/register?error";
        }
    }
}
