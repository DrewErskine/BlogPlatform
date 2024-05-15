package com.UserBlog.Blog.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UserAuthorityTest {

    @Test
    public void testUserWithAuthorities() {
        Authority authority1 = new Authority("USER");
        Authority authority2 = new Authority("ADMIN");

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority1);
        authorities.add(authority2);

        User user = new User();
        user.setUsername("testUser");
        user.setEmail("testUser@example.com");
        user.setPassword("password");
        user.setAuthorities(authorities);

        assertThat(user.getAuthorities()).containsExactlyInAnyOrder(authority1, authority2);
    }
}
