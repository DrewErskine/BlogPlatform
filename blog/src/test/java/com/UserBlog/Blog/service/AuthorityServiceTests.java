package com.UserBlog.Blog.service;

import com.UserBlog.Blog.model.Authority;
import com.UserBlog.Blog.model.User;
import com.UserBlog.Blog.repository.AuthorityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

public class AuthorityServiceTests {

    @Mock
    private AuthorityRepository authorityRepository;

    @InjectMocks
    private AuthorityService authorityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAssignAuthorityToUser() {
        User user = new User();
        user.setUsername("john_doe");
        user.setPassword("password123");

        Authority authority = new Authority();
        authority.setName("ROLE_USER");

        when(authorityRepository.save(any(Authority.class))).thenReturn(authority);

        Authority savedAuthority = authorityService.save(authority);
        assertThat(savedAuthority).isNotNull();
        assertThat(savedAuthority.getName()).isEqualTo("ROLE_USER");
    }

    @Test
    public void testGetAuthoritiesByUser() {
        User user = new User();
        user.setUsername("john_doe");
        user.setPassword("password123");

        Authority authority = new Authority();
        authority.setName("ROLE_USER");

        when(authorityRepository.findById(any(String.class))).thenReturn(Optional.of(authority));

        Optional<Authority> foundAuthority = authorityService.findByName("ROLE_USER");
        assertThat(foundAuthority).isPresent();
        assertThat(foundAuthority.get().getName()).isEqualTo("ROLE_USER");
    }
}
