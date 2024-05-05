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

    public boolean authenticate(String username, String password) {
        if (users.containsKey(username)) {
            String hashedPassword = users.get(username);
            return passwordEncoder.matches(password, hashedPassword);
        }
        return false;
    }

    public boolean registerUser(String username, String password) {
        if (!users.containsKey(username)) {
            String hashedPassword = passwordEncoder.encode(password);
            users.put(username, hashedPassword);
            return true;
        }
        return false;
    }


    public boolean changePassword(String username, String oldPassword, String newPassword) {
        if (users.containsKey(username)) {
            String hashedPassword = users.get(username);
            if (passwordEncoder.matches(oldPassword, hashedPassword)) {
                String newHashedPassword = passwordEncoder.encode(newPassword);
                users.put(username, newHashedPassword);
                return true;
            }
        }
        return false;
    }


    public boolean resetPassword(String username) {
        if (users.containsKey(username)) {
            String newPassword = generateRandomPassword();

            String newHashedPassword = passwordEncoder.encode(newPassword);
            users.put(username, newHashedPassword);
            return true;
        }
        return false;
    }

    private String generateRandomPassword() {
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
            .roles("USER")
            .build();
    }
}