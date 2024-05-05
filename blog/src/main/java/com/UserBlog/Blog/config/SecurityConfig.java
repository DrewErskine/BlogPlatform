package com.UserBlog.Blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;

import com.UserBlog.Blog.service.LoginService;

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
                       .roles("USER")
                       .build();
        var admin = User.withUsername("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles("ADMIN")
                        .build();
        var root = User.withUsername("root")
                        .password(passwordEncoder.encode("Devindrew42!"))
                        .roles("ROOT")
                        .build();
        return new InMemoryUserDetailsManager(user, admin, root);
    }

    // DaoAuthenticationProvider setup
    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder, LoginService loginService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    // AuthenticationManager bean
    @Bean
    public AuthenticationManager authManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, LoginService loginService) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
            .userDetailsService(loginService)
            .passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }
    

    // SecurityFilterChain setup with DaoAuthenticationProvider
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, DaoAuthenticationProvider authenticationProvider) throws Exception {
        http
            .authenticationProvider(authenticationProvider)
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/posts/**").authenticated() 
                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().permitAll())
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
