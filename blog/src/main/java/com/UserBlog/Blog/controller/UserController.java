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

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin() {
        return "redirect:/";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@Validated @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        boolean usernameExists = userService.existsByUsername(user.getUsername());
        boolean emailExists = userService.existsByEmail(user.getEmail());

        if (usernameExists || emailExists) {
            if (usernameExists) {
                result.rejectValue("username", "error.user", "An account with this username already exists.");
            }
            if (emailExists) {
                result.rejectValue("email", "error.user", "An account with this email already exists.");
            }
            return "register";
        }
    
        userService.save(user);
        return "redirect:/api/users/login";
    }    
}
