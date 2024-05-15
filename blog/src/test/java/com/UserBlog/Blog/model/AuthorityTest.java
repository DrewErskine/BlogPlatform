package com.UserBlog.Blog.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthorityTest {

    @Test
    public void testAuthorityCreation() {
        Authority authority = new Authority("USER");
        assertThat(authority.getName()).isEqualTo("USER");
    }

    @Test
    public void testAuthorityEquality() {
        Authority authority1 = new Authority("USER");
        Authority authority2 = new Authority("USER");

        assertThat(authority1).isEqualTo(authority2);
        assertThat(authority1.hashCode()).isEqualTo(authority2.hashCode());
    }
}
