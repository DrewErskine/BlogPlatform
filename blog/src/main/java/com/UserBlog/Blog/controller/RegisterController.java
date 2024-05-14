package com.UserBlog.Blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.service.UserService;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register-form"; 
    }
    
    @PostMapping("/register")
    public String processRegistration(@RequestParam String username, @RequestParam String password, @RequestParam String email, Model model) {
        try {
            userService.registerUser(username, password, email);
            return "redirect:/blogHome";
        } catch (IllegalArgumentException e) {
            model.addAttribute("registrationError", e.getMessage());
            return "register-form"; 
        } catch (Exception e) {
            model.addAttribute("registrationError", "An error occurred during registration.");
            return "redirect:/register?success"; 
        }
    }
}
