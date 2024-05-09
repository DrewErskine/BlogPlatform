package com.UserBlog.Blog.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User save(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Failed to save user: Data integrity violation", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save user", e);
        }
    }

        /**
     * Registers a new user if the username and email are not already taken.
     * @param username the username for the new user
     * @param password the user's password
     * @param email the user's email
     * @return true if registration is successful, false if username or email exists
     */
    @Transactional
    public boolean registerUser(String username, String password, String email) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists: " + username);
        }

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists: " + email);
        }

        if (!userRepository.existsByUsername(username) && !userRepository.existsByEmail(email)) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setEmail(email);
            newUser.setPassword(passwordEncoder.encode(password));
            newUser.setAuthorities(Collections.emptySet()); 
            userRepository.save(newUser);
            return true;
        }
        return false;
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

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            return findByUsername(username).orElse(null);
        }
        return null;
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
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Failed to update user: Data integrity violation", e);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user", e);
        }
    }
}
