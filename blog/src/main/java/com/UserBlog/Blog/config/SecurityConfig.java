package com.UserBlog.Blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/posts/**").authenticated() 
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll())
            .httpBasic(Customizer.withDefaults()); 

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    InMemoryUserDetailsManager userDetailsService() {
        var user = User.withUsername("user")
                       .password(passwordEncoder().encode("password"))
                       .roles("USER")
                       .build();
        var admin = User.withUsername("admin")
                        .password(passwordEncoder().encode("admin"))
                        .roles("ADMIN")
                        .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
