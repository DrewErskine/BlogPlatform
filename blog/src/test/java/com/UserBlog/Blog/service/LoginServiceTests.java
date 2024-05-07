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
        when(passwordEncoder.encode("luf")).thenReturn("encodedPassword1");
        when(passwordEncoder.encode("password2")).thenReturn("encodedPassword2");
    }

    @Nested
    class AuthenticateTests {

        @Test
        public void testAuthenticateWithValidCredentials() {
            // Mocking the users map
            Map<String, String> users = new HashMap<>();
            // Store the hashed password in the map
            users.put("drew", passwordEncoder.encode("luf"));
            loginService.setUsers(users);
        
            // Provide the plain text password to authenticate
            assertFalse(loginService.authenticate("drew", "luf"));
        }
        

        @Test
        public void testAuthenticateWithInvalidUsername() {
            assertFalse(loginService.authenticate("invalidUser", "password"));
        }

        @Test
        public void testAuthenticateWithInvalidPassword() {
            assertFalse(loginService.authenticate("drew", "invalidPassword"));
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
            assertTrue(loginService.registerUser("drew", "luf"));
        }
    }

    @Nested
    class ChangePasswordTests {

        @Test
        public void testChangePasswordWithInvalidUsername() {
            assertFalse(loginService.changePassword("invalidUser", "password", "newPassword"));
        }

        @Test
        public void testChangePasswordWithInvalidOldPassword() {
            assertFalse(loginService.changePassword("drew", "invalidPassword", "newPassword"));
        }
    }

    @Nested
    class ResetPasswordTests {

        @Test
        public void testResetPasswordWithExistingUsername() {
            assertFalse(loginService.resetPassword("drew"));
        }

        @Test
        public void testResetPasswordWithInvalidUsername() {
            assertFalse(loginService.resetPassword("invalidUser"));
        }
    }
}
