package com.UserBlog.Blog.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.service.UserService;
import com.UserBlog.Blog.service.LoginService;

@Controller
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final LoginService loginService;

    // Constructor injection for services
    public UserController(UserService userService, LoginService loginService) {
        this.userService = userService;
        this.loginService = loginService;
    }

    // Display login form
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Handle login data
    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password, Model model) {
        boolean isAuthenticated = loginService.authenticate(username, password);
        if (isAuthenticated) {
            return "redirect:/home"; 
        } else {
            model.addAttribute("loginError", "Invalid username or password.");
            return "login";
        }
    }

    // Display registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Process registration
    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        
        if (userService.existsByUsername(user.getUsername()) || userService.existsByEmail(user.getEmail())) {
            model.addAttribute("registrationError", "Username or email already exists.");
            return "register";
        }

        userService.save(user);
        return "redirect:/login";
    }
}
