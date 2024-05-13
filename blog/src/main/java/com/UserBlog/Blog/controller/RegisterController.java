package com.UserBlog.Blog.controller;

import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registerForm";  // Ensure this is different from the request path
    }

    @PostMapping("/register")
    public String registerUser(@javax.validation.Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "registerForm";  // Ensure this is different from the request path
        }
        userService.save(user);
        return "redirect:/register?success";  // Adjust this if your test expects a different redirect
    }
}
