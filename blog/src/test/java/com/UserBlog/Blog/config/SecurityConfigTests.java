package com.UserBlog.Blog.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@ActiveProfiles("test")
public class SecurityConfigTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private InMemoryUserDetailsManager userDetailsManager;

    @Autowired
    private AuthenticationManager authenticationManager; // Ensure this is configured if needed

    @Test
    void userRolesAreCorrect() {
        UserDetails user = userDetailsManager.loadUserByUsername("user");
        assertThat(user.getAuthorities()).extracting("authority").contains("ROLE_USER");
    }

    @Test
    void authenticationSucceedsForValidUser() {
        String username = "user";
        String password = "password"; // Ensure this is the raw password used in userDetailsService

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authToken);

        assertTrue(authentication.isAuthenticated());
    }
    
    

    @Test
    void passwordEncodingWorks() {
        String rawPassword = "myPassword";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        assertTrue(passwordEncoder.matches(rawPassword, encodedPassword));
    }
    

    @Test
    void authenticationFailsForInvalidPassword() {
        String username = "user";
        String wrongPassword = "wrongPassword";
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, wrongPassword);

        assertThrows(BadCredentialsException.class, () -> {
            authenticationManager.authenticate(token);
        });
    }

    @Test
    void userDetailsAreCorrect() {
        UserDetails user = userDetailsManager.loadUserByUsername("user");
        assertThat(user.getUsername()).isEqualTo("user");
        assertThat(user.isAccountNonExpired()).isTrue();
        assertThat(user.isAccountNonLocked()).isTrue();
        assertThat(user.isCredentialsNonExpired()).isTrue();
        assertThat(user.isEnabled()).isTrue();
    }
}
