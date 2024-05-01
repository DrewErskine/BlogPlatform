package com.UserBlog.Blog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@WebMvcTest(LoginService.class)
public class LoginServiceTests {

    @Autowired
    private LoginService loginService;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    public void setup() {
        // Mocking the behavior of password encoder
        when(passwordEncoder.encode("password1")).thenReturn("encodedPassword1");
        when(passwordEncoder.encode("password2")).thenReturn("encodedPassword2");
    }

    @Nested
    class AuthenticateTests {

        @Test
        public void testAuthenticateWithValidCredentials() {
            // Mocking the users map
            Map<String, String> users = new HashMap<>();
            // Store the hashed password in the map
            users.put("user1", passwordEncoder.encode("password1"));
            loginService.setUsers(users);
        
            // Provide the plain text password to authenticate
            assertFalse(loginService.authenticate("user1", "password1"));
        }
        

        @Test
        public void testAuthenticateWithInvalidUsername() {
            assertFalse(loginService.authenticate("invalidUser", "password"));
        }

        @Test
        public void testAuthenticateWithInvalidPassword() {
            assertFalse(loginService.authenticate("user1", "invalidPassword"));
        }
    }

    @Nested
    class RegisterUserTests {

        @Test
        public void testRegisterUserWithNewUsername() {
            assertTrue(loginService.registerUser("newUser", "newPassword"));
        }

        @Test
        public void testRegisterUserWithExistingUsername() {
            assertFalse(loginService.registerUser("user1", "password"));
        }
    }

    @Nested
    class ChangePasswordTests {

        @Test
        public void testChangePasswordWithValidCredentials() {
            assertTrue(loginService.changePassword("user1", "password1", "newPassword"));
        }

        @Test
        public void testChangePasswordWithInvalidUsername() {
            assertFalse(loginService.changePassword("invalidUser", "password", "newPassword"));
        }

        @Test
        public void testChangePasswordWithInvalidOldPassword() {
            assertFalse(loginService.changePassword("user1", "invalidPassword", "newPassword"));
        }
    }

    @Nested
    class ResetPasswordTests {

        @Test
        public void testResetPasswordWithExistingUsername() {
            assertTrue(loginService.resetPassword("user1"));
        }

        @Test
        public void testResetPasswordWithInvalidUsername() {
            assertFalse(loginService.resetPassword("invalidUser"));
        }
    }
}
