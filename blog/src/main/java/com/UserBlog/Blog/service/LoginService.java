package com.UserBlog.Blog.service;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService implements UserDetailsService {

    private final Map<String, String> users = new HashMap<>();
    private final BCryptPasswordEncoder passwordEncoder;

    public LoginService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

        // Initialize with dummy users
        users.put("user1", passwordEncoder.encode("password1"));
        users.put("user2", passwordEncoder.encode("password2"));
    }

    public Map<String, String> getUsers() {
        return users;
    }

    public void setUsers(Map<String, String> users) {
        this.users.clear();
        this.users.putAll(users);
    }

    // Authenticate user
    public boolean authenticate(String username, String password) {
        // Check if user exists
        if (users.containsKey(username)) {
            // Check if password matches
            String hashedPassword = users.get(username);
            return passwordEncoder.matches(password, hashedPassword);
        }
        return false;
    }

    // Register new user
    public boolean registerUser(String username, String password) {
        // Check if user already exists
        if (!users.containsKey(username)) {
            // Hash the password before storing
            String hashedPassword = passwordEncoder.encode(password);
            users.put(username, hashedPassword);
            return true;
        }
        return false; // User already exists
    }

    // Change user password
    public boolean changePassword(String username, String oldPassword, String newPassword) {
        // Check if user exists
        if (users.containsKey(username)) {
            // Check if old password matches
            String hashedPassword = users.get(username);
            if (passwordEncoder.matches(oldPassword, hashedPassword)) {
                // Hash the new password before updating
                String newHashedPassword = passwordEncoder.encode(newPassword);
                users.put(username, newHashedPassword);
                return true; // Password updated successfully
            }
        }
        return false; // User not found or old password is incorrect
    }

    // Reset user password
    public boolean resetPassword(String username) {
        // Check if user exists
        if (users.containsKey(username)) {
            // Generate a new random password or implement your own logic
            String newPassword = generateRandomPassword(); // Example: Generate a random password
            // Hash the new password before updating
            String newHashedPassword = passwordEncoder.encode(newPassword);
            users.put(username, newHashedPassword);
            return true; // Password reset successfully
        }
        return false; // User not found
    }

    // Generate a random password (dummy implementation)
    private String generateRandomPassword() {
        // Implement your own logic to generate a random password
        return "newPassword123";
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!users.containsKey(username)) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        String password = users.get(username);
        return User.builder()
            .username(username)
            .password(password)
            .roles("USER") // You can customize roles based on your application needs
            .build();
    }
}