package com.UserBlog.Blog.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.UserBlog.Blog.service.UserService;
import com.UserBlog.Blog.model.User;

import java.util.Optional;

@Controller
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    // Constructor injection, Autowired is optional in single constructor scenarios
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String userProfile(Model model) {
        Optional<User> user = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "user/profile";
    }

    // Display user profile
    @PreAuthorize("isAuthenticated() && (#userId == principal.id || hasAuthority('ADMIN'))")
    @GetMapping("/profile/{userId}")
    public String userProfile(@PathVariable Long userId, Model model) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            return "user/profile";
        } else {
            model.addAttribute("errorMessage", "User not found.");
            return "error";
        }
    }

    @GetMapping("/edit-profile")
    public String editUserProfile(Model model) {
        Optional<User> user = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "user/edit-profile";
    }

    // Process the form submission for editing user profile
    @PostMapping("/edit-profile")
    public String updateUserProfile(@ModelAttribute User userForm, Model model) {
        try {
            userService.updateUser(userForm);
            model.addAttribute("successMessage", "Profile updated successfully!");
            return "redirect:/api/user/profile/" + userForm.getId();
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error updating profile: " + e.getMessage());
            return "user/edit-profile";
        }
    }
}
