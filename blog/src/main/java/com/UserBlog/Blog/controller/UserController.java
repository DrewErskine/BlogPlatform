package com.UserBlog.Blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.service.UserService;

@Controller
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    // Constructor for dependency injection
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @PostMapping("/login")
    public String processLogin() {
        return "redirect:/";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register.html";
    }

    @PostMapping("/register")
    public String processRegistration(@Validated @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register.html";
        }
    
        if (userService.existsByUsername(user.getUsername()) || userService.existsByEmail(user.getEmail())) {
            result.rejectValue("username", "error.user", "Username or email already exists");
            return "register.html";
        }
    
        userService.save(user);
        return "redirect:/api/users/login";
    }    
}
