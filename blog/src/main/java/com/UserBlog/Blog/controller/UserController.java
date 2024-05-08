package com.UserBlog.Blog.controller;

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

    // Display user profile
    @GetMapping("/profile/{userId}")
    public String userProfile(@PathVariable Long userId, Model model) {
        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            return "user/profile";
        } else {
            model.addAttribute("errorMessage", "User not found.");
            return "error";  // Assuming there's an 'error.html' template to handle errors
        }
    }

    @GetMapping("/edit-profile")
    public String editUserProfile(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "user/edit-profile";
    }

    // Process the form submission for editing user profile
    @PostMapping("/edit-profile")
    public String updateUserProfile(@ModelAttribute User userForm, Model model) {
        try {
            userService.updateUser(userForm);
            model.addAttribute("successMessage", "Profile updated successfully!");
            return "redirect:/api/user/profile/" + userForm.getId();  // Redirect to the updated profile
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error updating profile: " + e.getMessage());
            return "user/edit-profile";  // Stay on the edit page if there's an error
        }
    }
}
