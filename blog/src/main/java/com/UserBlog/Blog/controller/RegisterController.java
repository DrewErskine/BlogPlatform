package com.UserBlog.Blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.UserBlog.Blog.model.User;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public ModelAndView showRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register"); // Assuming "register" is the name of your registration view
        modelAndView.addObject("user", new User()); // Assuming you have a User class representing user data
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView processRegistration(@ModelAttribute("user") User user) {
        // Process registration logic here, e.g., saving user data to the database
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/login"); // Redirect to login page after successful registration
        return modelAndView;
    }
}
