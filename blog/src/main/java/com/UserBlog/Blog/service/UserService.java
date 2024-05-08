package com.UserBlog.Blog.service;

import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Checks if a username exists in the database.
     * @param username the username to check
     * @return true if the username exists, false otherwise
     */
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    /**
     * Checks if an email exists in the database.
     * @param email the email to check
     * @return true if the email exists, false otherwise
     */
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    /**
     * Saves a user to the database with an encoded password.
     * @param user the user to save
     * @return the saved user
     */
    @Transactional
    public User save(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (Exception e) {
            // Log and/or handle the exception appropriately
            throw new RuntimeException("Failed to save user", e);
        }
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

        public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            return findByUsername(username).orElse(null);
        }
        return null; // Or handle appropriately
    }

    /**
     * Finds a user by username.
     * @param username the username to search for
     * @return an Optional containing the found user if available
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Finds a user by email.
     * @param email the email to search for
     * @return an Optional containing the found user if available
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Updates a user in the database.
     * @param user the user to update
     * @return the updated user
     */
    @Transactional
    public User updateUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            // Log and/or handle the exception appropriately
            throw new RuntimeException("Failed to update user", e);
        }
    }
}
