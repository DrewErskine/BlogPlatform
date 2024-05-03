package com.UserBlog.Blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // InMemoryUserDetailsManager setup with encoded passwords
    @Bean
    InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        var user = User.withUsername("user")
                       .password(passwordEncoder.encode("password"))
                       .roles("USER")  // Automatically translates to ROLE_USER
                       .build();
        var admin = User.withUsername("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles("ADMIN") // Automatically translates to ROLE_ADMIN
                        .build();
        var root = User.withUsername("root")
                        .password(passwordEncoder.encode("Devindrew42!"))
                        .roles("ROOT")  // Assign appropriate roles
                        .build();
        return new InMemoryUserDetailsManager(user, admin, root);
    }

    // DaoAuthenticationProvider setup
    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, InMemoryUserDetailsManager userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    // SecurityFilterChain setup with DaoAuthenticationProvider
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, DaoAuthenticationProvider authenticationProvider) throws Exception {
        http
            .authenticationProvider(authenticationProvider)
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/posts/**").authenticated() 
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")  // Using hasAuthority with ROLE_
                .anyRequest().permitAll())
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
