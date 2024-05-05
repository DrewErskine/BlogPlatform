package com.UserBlog.Blog.service;

import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return user;
    }

    public boolean registerNewUserAccount(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return false; // Or throw an exception if you prefer
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        return userRepository.findByUsername(username)
                .filter(user -> passwordEncoder.matches(oldPassword, user.getPassword()))
                .map(user -> {
                    user.setPassword(passwordEncoder.encode(newPassword));
                    userRepository.save(user);
                    return true;
                }).orElse(false);
    }

    public boolean resetPassword(String username) {
        return userRepository.findByUsername(username)
                .map(user -> {
                    String newPassword = generateRandomPassword();
                    user.setPassword(passwordEncoder.encode(newPassword));
                    userRepository.save(user);
                    return true; // Consider returning the new password or a password reset link
                }).orElse(false);
    }

    private String generateRandomPassword() {
        // Implement a more secure password generation logic
        return "newPassword123";
    }
}
